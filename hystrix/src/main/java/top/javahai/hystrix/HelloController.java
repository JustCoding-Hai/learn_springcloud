package top.javahai.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.javahai.model.User;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Hai
 * @date 2020/7/21 - 0:27
 */
@RestController
public class HelloController {

  @Autowired
  RestTemplate restTemplate;
  @Autowired
  HelloService helloService;

  @GetMapping("/hello")
  public String hello(){
    return helloService.hello();
  }

  /**
   * 请求命令，
   * 需要注意一次实例只能够执行一次
   * 可以选择直接执行或者先入队后再执行
   * @return
   */
  @GetMapping("/hello2")
  public String hello2(){
    HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
    //通过execute方法执行
    HelloCommand helloCommand = new HelloCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("hai")), restTemplate,"test_cache");
    String result01 = helloCommand.execute();
    //通过queue.get执行，先入队再执行
    HelloCommand helloCommand1 = new HelloCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("hai2")), restTemplate,"test_cache");
    Future<String> queue = helloCommand1.queue();
    String result02=" ";
    try {
      result02 = queue.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    hystrixRequestContext.close();
    return result01+" "+result02;
  }

  /**
   * 通过注解方式实现请求的异步调用
   * @return
   * @throws ExecutionException
   * @throws InterruptedException
   */
  @GetMapping("/hello3")
  public String hello3() throws ExecutionException, InterruptedException {
    Future<String> stringFuture = helloService.hello2();
    String result = stringFuture.get();
    return result;
  }

  /**
   * 测试缓存
   * 这里涉及缓存生命周期的概念
   * 首先初始化HystrixRequestContext，初始化完成后，缓存开始生效
   * 然后调用HystrixRequestContext缓存失效
   * @return
   */
  @GetMapping("/hello4")
  public String hello4(){
    HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
    //第一次请求，缓存了结果
    String s=helloService.helloCache("hai");
    //再次发起请求，这次不会请求provider服务的接口，而是直接获取缓存中的结果
    s=helloService.helloCache("hai");
    //删除了数据同时删除了缓存中的数据
    helloService.deleteUser("hai");
    //再次发起请求
    s=helloService.helloCache("hai");
    hystrixRequestContext.close();
    return s;
  }

  @Autowired
  UserService userService;

  @GetMapping("/hello5")
  public void hello5() throws ExecutionException, InterruptedException {
    HystrixRequestContext ctx = HystrixRequestContext.initializeContext();
    UserMergeCommand cmd1 = new UserMergeCommand(userService, 99);
    UserMergeCommand cmd2 = new UserMergeCommand(userService, 98);
    UserMergeCommand cmd3 = new UserMergeCommand(userService, 97);
    UserMergeCommand cmd4 = new UserMergeCommand(userService, 96);
    Future<User> q1 = cmd1.queue();
    Future<User> q2 = cmd2.queue();
    Future<User> q3 = cmd3.queue();
    Future<User> q4 = cmd4.queue();
    User u1 = q1.get();
    User u2 = q2.get();
    User u3 = q3.get();
    User u4 = q4.get();
    System.out.println(u1);
    System.out.println(u2);
    System.out.println(u3);
    System.out.println(u4);
    ctx.close();
  }



  @GetMapping("/hello6")
  public void hello6() throws ExecutionException, InterruptedException {
    HystrixRequestContext ctx = HystrixRequestContext.initializeContext();
    Future<User> q1 = userService.getUserById(99);
    Future<User> q2 = userService.getUserById(98);
    Future<User> q3 = userService.getUserById(97);
    User u1 = q1.get();
    User u2 = q2.get();
    User u3 = q3.get();
    System.out.println(u1);
    System.out.println(u2);
    System.out.println(u3);
    Thread.sleep(2000);
    Future<User> q4 = userService.getUserById(96);
    User u4 = q4.get();
    System.out.println(u4);
    ctx.close();
  }

}
