package top.javahai.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ZipkinApplication {

  public static void main(String[] args) {
    SpringApplication.run(ZipkinApplication.class, args);
  }


  @Bean
  RestTemplate restTemplate(){
    return new RestTemplate();
  }
}
