package top.javahai.consulprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hai
 * @date 2020/7/20 - 20:51
 */
@RestController
public class HelloController {

  @Value("${server.port}")
  Integer port;

  @GetMapping("/hello")
  public String hello(){
    return "hello" + port;
  }
}
