package top.javahai.springcloud.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hai
 * @program: learn_springcloud
 * @description:
 * @create 2022/4/10 - 18:11
 **/
@RestController
@RefreshScope
public class TestController {

    @Value("${name}")
    public String name;

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/hello")
    public String hello() {
        return name;
    }

    @GetMapping("/test")
    public String test() {
        return "nacos-discovery:"+port;
    }


}
