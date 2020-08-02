package top.javahai.openfeign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.javahai.model.User;
import top.javahai.openfeign.service.HelloService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Ethan Huang
 * @create 2020-07-29 16:09
 */
@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello(){
        return helloService.hello();
    }
    @GetMapping("/hello2")
    public String hello2(){
        return helloService.hello2("Ethan");
    }

    /**
     * 测试各类请求的远程调用
     */
    @GetMapping("/test_param")
    public void testParam() throws UnsupportedEncodingException {
        User user = new User();
        user.setId(1);
        user.setUsername("zhang2");
        user.setPassword("321");
        User user1 = helloService.insertUser(user);
        helloService.deleteUserById(2);
        helloService.updateUser(2,"Ethan","321");
        helloService.getUserByName(URLEncoder.encode("不会吧","UTF-8"));
        System.out.println(user1);
    }
}
