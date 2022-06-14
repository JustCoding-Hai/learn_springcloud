package top.javahai.springcloud.consul.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hai
 * @program: learn_springcloud
 * @description:
 * @create 2022/5/3 - 17:31
 **/
@RestController
public class HelloController {
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello")
    public void hello() {
        ServiceInstance choose = loadBalancerClient.choose("consul-provider");
        System.out.println("服务地址：" + choose.getUri());
        System.out.println("服务名称:" + choose.getServiceId());
        String s = restTemplate.getForObject(choose.getUri() + "/hello",
                String.class);
        System.out.println(s);
    }
}
