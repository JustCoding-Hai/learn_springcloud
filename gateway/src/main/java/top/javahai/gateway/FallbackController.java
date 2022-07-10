package top.javahai.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hai
 * @program: learn_springcloud
 * @description:
 * @create 2022/7/3 - 17:52
 **/
@RestController
public class FallbackController {


    @GetMapping("/fallback")
    public Object fallback() {
        Map<String,Object> result = new HashMap<>();
        result.put("data",null);
        result.put("message","get request fallback!");
        result.put("code",500);
        return result;
    }
}
