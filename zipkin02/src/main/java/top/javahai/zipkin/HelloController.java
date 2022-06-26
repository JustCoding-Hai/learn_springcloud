package top.javahai.zipkin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hai
 * @program: learn_springcloud
 * @description:
 * @create 2022/6/26 - 1:26
 **/
@RestController
public class HelloController {

    @Autowired
    RestTemplate restTemplate;


    private static final Logger logger =
            LoggerFactory.getLogger(HelloController.class);
    @GetMapping("/hello")
    public String hello() {
        logger.info("zipkin02-hello");
        String s = restTemplate.getForObject("http://localhost:8080/hello?name={1}",String.class, "hello world");
        logger.info(s);
        return s;
    }
}
