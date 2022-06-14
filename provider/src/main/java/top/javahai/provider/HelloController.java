package top.javahai.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import top.javahai.model.User;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author Hai
 * @date 2020/7/17 - 14:18
 */
@RestController
public class HelloController {
  @Value("${server.port}")
  Integer port;

  @GetMapping("/hello")
  public String hello(){
    System.out.println(new Date());

    return "hello world port:"+port;
  }

  @GetMapping("/test_get")
  public String hell02(String name){
    System.out.println(new Date()+"-"+name);
    return "Hello "+name;
  }
  /**
   * 测试post,接收key/value形式的参数
   */
  @PostMapping("/user1")
  public User addUser(User user){
    return user;
  }
  /**
   * 测试post，接收json形式的参数
   */
  @PostMapping("/user2")
  public User addUser2(@RequestBody User user){
    return user;
  }

  /**
   * 测试put，接收key/value形式的参数
   * @param user
   */
  @PutMapping("/user1")
  public void updateUser(User user){
    System.out.println(user);
  }

  /**
   * 测试put，接收json格式的参数
   * @param user
   */
  @PutMapping("/user2")
  public void updateUser2(@RequestBody User user){
    System.out.println(user);
  }

  /**
   * 测试delete,接收key/value
   * @param id
   */
  @DeleteMapping("/user1")
  public void deleteUser1(Integer id){
    System.out.println(id);
  }

  /**
   * 测试delete，在路径上直接写参数
   * @param id
   */
  @DeleteMapping("/user2/{id}")
  public void deleteUser2(@PathVariable Integer id){
    System.out.println(id);
  }

  @GetMapping("/user3")
  public void getUserByName(@RequestHeader("name") String name) throws UnsupportedEncodingException {
    System.out.println("get header property of  name:"+ URLDecoder.decode(name,"UTF-8"));
  }

}
