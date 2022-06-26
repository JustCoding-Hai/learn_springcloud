package top.javahai.sleuth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hai
 * @program: learn_springcloud
 * @description:
 * @create 2022/6/25 - 15:36
 **/
@RestController
public class HelloController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HelloService helloService;

    private static final Log log = LogFactory.getLog(HelloController.class);
    @GetMapping("/hello")
    public String hello() {
        log.info("hello spring cloud sleuth");
        return "hello spring cloud sleuth";
    }

    @GetMapping("/hello2")
    public String hello2() throws InterruptedException {
        log.info("hello2");
        Thread.sleep(500);
        return restTemplate.getForObject("http://localhost:8080/hello3",
                String.class);
    }
    @GetMapping("/hello3")
    public String hello3() throws InterruptedException {
        log.info("hello3");
        Thread.sleep(500);
        return "hello 3";
    }

    @GetMapping("/hello4")
    public String hello4() throws InterruptedException {
        log.info("hello4");
        return helloService.asyncFun();
    }



}
