package top.javahai.learn.springcloud.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hai
 * @program: learn_springcloud
 * @description:
 * @create 2022/10/5 - 13:08
 **/
@RestController
public class HelloController {

    /**
     * 获取配置文件的env值
     */
    @Value("${env}")
    String value;

    @GetMapping("/hello")
    public String hello() {
        return value;
    }

}
