package top.javahai.consulprovider;

import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient//开启服务发现功能
public class ConsulProviderApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConsulProviderApplication.class, args);
  }

}
