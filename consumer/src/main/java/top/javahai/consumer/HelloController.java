package top.javahai.consumer;

import org.apache.http.HttpConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.javahai.model.User;

import javax.ws.rs.GET;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Hai
 * @date 2020/7/17 - 14:24
 */
@RestController
public class HelloController {
  /**
   * 写死的形式访问其他进程的服务
   * @return
   */
  @GetMapping("/hello1")
  public String hello1(){
    HttpURLConnection httpConnection=null;
    try {
      URL url = new URL("http://localhost:1113/hello");
      httpConnection= (HttpURLConnection) url.openConnection();
      BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
      String s = reader.readLine();
      return s;
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {

    }
    return "error";
  }

  /**
   * 获取注册中心中的已注册的服务
   */
  @Autowired
  DiscoveryClient discoveryClient;
  @Autowired
  RestTemplate restTemplate;
  @GetMapping("/hello2")
  public String hello2(){
    HttpURLConnection httpConnection=null;
    //如果是集群部署会有多个
    List<ServiceInstance> providerList = discoveryClient.getInstances("provider");
    ServiceInstance provider=providerList.get(0);
    String host = provider.getHost();
    int port=provider.getPort();
    StringBuffer providerUrl = new StringBuffer("http://").append(host)
            .append(":")
            .append(port)
            .append("/hello");
    //通过RestTemplate实现服务的调用
    String result = restTemplate.getForObject(providerUrl.toString(), String.class);
    return result;
  }


  /**
   * 手动编写线性负载均衡
   */
  int count=0;
  @GetMapping("/hello3")
  public String hello3(){
    HttpURLConnection httpConnection=null;
    //如果是集群部署会有多个
    List<ServiceInstance> providerList = discoveryClient.getInstances("provider");
    ServiceInstance provider=providerList.get((count++)%providerList.size());
    String host = provider.getHost();
    int port=provider.getPort();
    StringBuffer providerUrl = new StringBuffer("http://").append(host)
            .append(":")
            .append(port)
            .append("/hello");
    try {
      URL url = new URL( providerUrl.toString());
      httpConnection= (HttpURLConnection) url.openConnection();
      BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
      String s = reader.readLine();
      return s;
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {

    }
    return "error";
  }
  @Autowired
  @Qualifier("balancedRestTemplate")
  RestTemplate balancedRestTemplate;

  /**
   * 使用Ribbon快速实现负载均衡（注入的RestTemple前加@LoadBalanced注解）
   * @return
   */
  @GetMapping("/hello4")
  public String hello4(){
    return balancedRestTemplate.getForObject("http://provider/hello",String.class);
  }

  /**
   * 测试RestTemplate的get请求使用
   */
  @GetMapping("/test_get")
  public void testGet(){
    String s1 = balancedRestTemplate.getForObject("http://provider/test_get?name={1}", String.class, "kobe");
    System.out.println(s1);
    ResponseEntity<String> entity = balancedRestTemplate.getForEntity("http://provider/test_get?name={1}", String.class, "hai");
    System.out.println("返回的数据body："+entity.getBody());
    System.out.println("响应实体："+entity.getStatusCode());
    System.out.println("响应码："+entity.getStatusCodeValue());
    System.out.println("响应头信息：");
    HttpHeaders headers = entity.getHeaders();
    Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
    for (Map.Entry<String, List<String>> entry : entries) {
      System.out.println(entry.getKey()+" : "+entry.getValue());
    }
    /*
      测试Get请求的相关方法的重载方法
     */
    //多个参数示例
    //balancedRestTemplate.getForEntity("http://provider/test_get?name={1}&id={2}",String.class,"2","s");
    //使用map
    Map<String,Object> map=new HashMap<>();
    map.put("name","everyone");
    String s2 = balancedRestTemplate.getForObject("http://provider/test_get?name={name}", String.class, map);
    System.out.println(s2);
    //使用url
    String url="http://provider/test_get?name=dog";
    URI uri = URI.create(url);
    String s3 = balancedRestTemplate.getForObject(uri, String.class);
    System.out.println(s3);
  }
  /**
   * 测试post相关方法
   */
  @GetMapping("/test_post")
  public void testPost(){
    MultiValueMap<String,Object> map=new LinkedMultiValueMap<>();
    map.add("id",1);
    map.add("username","kobe");
    map.add("password","123");
    User user = balancedRestTemplate.postForObject("http://provider/user1", map, User.class);
    System.out.println(user);
    user.setId(2);
    User user2 = balancedRestTemplate.postForObject("http://provider/user2", user, User.class);
    System.out.println(user2);
    //获取重定向的uri，再发送请求
    URI uri = balancedRestTemplate.postForLocation("http://provider/register", map);
    String s = balancedRestTemplate.getForObject(uri, String.class);
    System.out.println(s);
  }

  /**
   * 测试put相关方法
   */
  @GetMapping("/test_put")
  public void testPut(){
    MultiValueMap<String,Object> map=new LinkedMultiValueMap<>();
    map.add("id",2);
    map.add("username","aaa");
    map.add("password","321");
    balancedRestTemplate.put("http://provider/user1",map);
    User user = new User();
    user.setId(1);
    user.setUsername("sad");
    user.setPassword("321");
    balancedRestTemplate.put("http://provider/user2",user);
  }
  /**
   * 测试delete相关方法
   */
  @GetMapping("/test_delete")
  public void testDelete(){
    balancedRestTemplate.delete("http://provider/user1?id={1}",23);
    balancedRestTemplate.delete(("http://provider/user2/24"));
  }
}
