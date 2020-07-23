package top.javahai.provider;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.javahai.model.User;

/**
 * @author Hai
 * @date 2020/7/18 - 21:30
 */
@Controller
public class RegisterController {
  /**
   * 注册接口， 成功注册后自动跳转到登录页面
   * @param user
   * @return
   */
  @PostMapping("/register")
  public String register(User user){
    return "redirect:http://provider/loginPage?username="+user.getUsername();
  }
  /**
   * 登录页面
   * @param username
   * @return
   */
  @GetMapping("/loginPage")
  @ResponseBody
  public String loginPage(String username){
    return "loginPage:"+username;
  }
}
