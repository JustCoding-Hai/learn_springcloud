package top.javahai.hystrix;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.javahai.model.User;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @author Hai
 * @date 2020/7/22 - 22:41
 */
@Service
public class UserService {
  @Autowired
  RestTemplate restTemplate;

  public List<User> getUsersByIds(List<Integer> ids){
    User[] users=restTemplate.getForObject("http://provider/user/",User[].class, StringUtils.join(ids,","));
    return Arrays.asList(users);
  }

}
