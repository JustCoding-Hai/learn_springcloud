package top.javahai.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import top.javahai.model.User;

import java.util.List;

/**
 * 请求合并
 * @author Hai
 * @date 2020/7/22 - 23:48
 */
public class UserBatchCommand extends HystrixCommand<List<User>> {
  private List<Integer> ids;
  private UserService userService;

  public UserBatchCommand(List<Integer> ids,UserService userService) {
    super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("batchCmd")).andCommandKey(HystrixCommandKey.Factory.asKey("batchKey")));
    this.ids=ids;
    this.userService=userService;
  }

  @Override
  protected List<User> run() throws Exception {
    return userService.getUsersByIds(ids);
  }
}
