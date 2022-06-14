package top.javahai.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.javahai.model.User;

import javax.ws.rs.GET;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hai
 * @date 2020/7/22 - 22:38
 */
@RestController
@RequestMapping("/user")
public class UserController {
  @GetMapping("/{ids}")
  public List<User> getUsersByIds(@PathVariable String ids){
    System.out.println("接收到请求："+ids);
    String[] split = ids.split(",");
    List<User> users = new ArrayList<>();
    for (String id : split) {
      User user = new User();
      user.setId(Integer.parseInt(id));
      users.add(user);
    }
    return users;
  }
}
