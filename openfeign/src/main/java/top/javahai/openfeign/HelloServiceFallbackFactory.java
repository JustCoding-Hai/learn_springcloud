package top.javahai.openfeign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import top.javahai.model.User;
import top.javahai.openfeign.service.HelloService;

/**
 * @author Hai
 * @program: learn_springcloud
 * @description:
 * @create 2022/4/23 - 22:30
 **/
@Component
public class HelloServiceFallbackFactory implements FallbackFactory<HelloService> {
    @Override
    public HelloService create(Throwable throwable) {
        return new HelloService() {
            @Override
            public String hello() {
                return "error";
            }

            @Override
            public String hello2(String name) {
                return "error2";
            }

            @Override
            public User insertUser(User user) {
                return null;
            }

            @Override
            public void deleteUserById(Integer id) {

            }

            @Override
            public void updateUser(Integer id, String username, String password) {

            }

            @Override
            public void getUserByName(String name) {

            }
        };
    }
}
