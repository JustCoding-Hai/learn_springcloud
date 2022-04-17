package top.javahai.springcloud.nacos.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hai
 * @program: learn_springcloud
 * @description:
 * @create 2022/4/14 - 23:48
 **/
@RestController
public class TestController {

    private final RestTemplate restTemplate;

    @Autowired
    public TestController(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return restTemplate.getForObject("http://nacos-provider/test", String.class);
    }
}
