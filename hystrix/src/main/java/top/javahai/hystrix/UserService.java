package top.javahai.hystrix;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.javahai.model.User;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Hai
 * @date 2020/7/22 - 22:41
 */
@Service
public class UserService {
  @Autowired
  RestTemplate restTemplate;

  @HystrixCollapser(batchMethod = "getUsersByIds", collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds",value = "200")})
  public Future<User> getUserById(Integer id) {
    return null;
  }

  @HystrixCommand
  public List<User> getUsersByIds(List<Integer> ids){
    User[] users=restTemplate.getForObject("http://provider/user/{1}",User[].class, StringUtils.join(ids,","));
    return Arrays.asList(users);
  }

}
