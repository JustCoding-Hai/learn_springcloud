package top.javahai.openfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import top.javahai.model.User;
import top.javahai.openfeign.HelloServiceFallback;

import javax.ws.rs.PUT;

/**
 * 定义接口
 * 1.通过@FeignClient指定要调用的是那个服务
 * 2.定义@GetMapping等注解指定要调用服务的那个注解
 * 3.凡是key/vaule形式的参数，一定要标记参数的名称（使用@RequestParam）,否则会出现找不到方法的错误
 * @author Ethan Huang
 * @create 2020-07-29 16:07
 */
@FeignClient(value = "provider",fallback = HelloServiceFallback.class)
public interface HelloService {
    /**
     * 请求provider的“/hello接口”
     * @return
     */
    @GetMapping("/hello")
    public String hello();

    /**
     * 请求带key/value的方法
     * @param name
     * @return
     */
    @GetMapping("/test_get")
    public String hello2(@RequestParam("name") String name);

    /**
     * 请求“/user2”接口，参数为json格式的
     * @param user
     * @return
     */
    @PostMapping("/user2")
    public User insertUser(@RequestBody User user);

    /**
     * 测试“/user2/{id}”
     * 注意@PathVariable("id")
     * @param id
     */
    @DeleteMapping("/user2/{id}")
    public void deleteUserById(@PathVariable("id") Integer id);

    @PutMapping("/user1")
    public void updateUser(@RequestParam("id") Integer id,@RequestParam("username") String username,@RequestParam("password") String password);


    /**
     * @RequestHeader的用法
     * 测试注解在Header中传递参数
     * @param name
     */
    @GetMapping("/user3")
    public void getUserByName(@RequestHeader("name") String name);

}
