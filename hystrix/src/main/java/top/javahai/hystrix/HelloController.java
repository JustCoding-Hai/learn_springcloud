package top.javahai.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
   * 可以直接执行或者先入队后再执行
   * @return
   */
  @GetMapping("/hello2")
  public String hello2(){
    //通过execute方法执行
    HelloCommand helloCommand = new HelloCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("hai")), restTemplate);
    String result01 = helloCommand.execute();
    //通过queue.get执行，先入队再执行
    HelloCommand helloCommand1 = new HelloCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("hai2")), restTemplate);
    Future<String> queue = helloCommand1.queue();
    String result02=" ";
    try {
      result02 = queue.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return result01+" "+result02;
  }
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
    //删除了数据同时删除了缓存中的数据
    helloService.deleteUser("hai");
    //再次发起请求
    s=helloService.helloCache("hai");
    hystrixRequestContext.close();
    return s;
  }

}
