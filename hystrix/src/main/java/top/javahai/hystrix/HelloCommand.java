package top.javahai.hystrix;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * 以继承类的方式来代替前面的注解方式来进行远程调用和服务熔断与降级
 * @author Hai
 * @date 2020/7/21 - 20:02
 */
public class HelloCommand extends HystrixCommand<String> {
  RestTemplate restTemplate;

  protected HelloCommand(Setter setter, RestTemplate restTemplate) {
    super(setter);
    this.restTemplate=restTemplate;
  }

  @Override
  protected String run() throws Exception {
    int i=1/0;
    return restTemplate.getForObject("http://provider/hello",String.class);
  }

  /**
   * 继承类的方式中实现服务的容错
   * 方法调用失败时会调用该方法。通过getExecutionException获取发生的异常
   * @return
   */
  @Override
  protected String getFallback() {
    return "Error-Extends:"+getExecutionException().toString();
  }
}
