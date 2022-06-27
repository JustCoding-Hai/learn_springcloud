package top.javahai.consul.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hai
 * @program: learn_springcloud
 * @description:
 * @create 2022/6/27 - 23:35
 **/
@RestController
@RefreshScope
public class ConfigController {


    @Value("${config.info:}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return configInfo;
    }
}
