package top.javahai.openfeign;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import top.javahai.model.User;
import top.javahai.openfeign.service.HelloService;

/**
 * @author Ethan Huang
 * @create 2020-07-31 9:48
 */
@Component
@RequestMapping("/error")
public class HelloServiceFallback implements HelloService {
    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello2(String name) {
        return "error";
    }

    @Override
    public User insertUser(User user) {
        return new User();
    }

    @Override
    public void deleteUserById(Integer id) {
        System.out.println("error");
    }

    @Override
    public void updateUser(Integer id, String username, String password) {
        System.out.println("error");
    }

    @Override
    public void getUserByName(String name) {
        System.out.println("error");
    }
}
