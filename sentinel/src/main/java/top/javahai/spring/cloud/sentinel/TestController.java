package top.javahai.spring.cloud.sentinel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hai
 * @program: learn_springcloud
 * @description:
 * @create 2022/4/17 - 21:20
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public String test () {
        return "test";
    }
}
