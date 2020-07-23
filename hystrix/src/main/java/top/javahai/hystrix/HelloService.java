package top.javahai.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * @author Hai
 * @date 2020/7/21 - 0:24
 */
@Service
public class HelloService {
  @Autowired
  RestTemplate restTemplate;

  /**
   *方法中发起远程调用可能会出现调用失败
   * 使用@HystrixCommand注解，配置fallbackMethod属性用于方法调用失败后的临时替代方法
   * @return
   */
  @HystrixCommand(fallbackMethod = "error")
  public String hello(){
    int i=1/0;
    return restTemplate.getForObject("http://provider/hello",String.class);
  }

  /**
   *这个方法的名字需要与fallbackMethod中一致。方法返回值也需要与注解所修饰的方法一致
   * 通过参数Throwable获取在发生错误的方法中抛出的异常
   * @return
   */
  public String error(Throwable throwable){
    return "error:"+throwable.toString();
  }

  /**
   * 通过注解方式实现请求异步调用
   */
  @HystrixCommand(fallbackMethod = "error")
   public Future<String> hello2(){
     return new AsyncResult<String>() {
       @Override
       public String invoke() {
         return restTemplate.getForObject("http://provider/hello",String.class);
       }
     };
   }

  /**
   * 测试请求缓存
   * 1.@CacheRequest注解表示该方法的请求结果会被缓存起来，默认情况下，缓存的key
   * 就是方法的参数，缓存的value就是方法的返回值
   * 2.@CacheKey注解的作用，就是方法有多个参数时，默认是组合这些参数作为key，但可以
   * 使用@CacheKey注解来指定某个参数为key
   * @param name
   * @return
   */
   @HystrixCommand(fallbackMethod = "error2")
   @CacheResult
   public String helloCache(String name){
     //发送两次请求
     String result01 = restTemplate.getForObject("http://provider/test_get?name={1}", String.class, name);
     return result01;

   }
   public String error2(String name){
    return "error:helloCache";
   }
  /**
   * 测试@CacheRemove
   */
  @HystrixCommand
  @CacheRemove(commandKey = "helloCache")
  public void deleteUser(String name){
    System.out.println("删除了记录"+name);
    System.out.println("缓存清理");
  }

}
