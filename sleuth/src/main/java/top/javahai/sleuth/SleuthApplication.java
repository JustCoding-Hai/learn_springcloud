package top.javahai.sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//开启异步任务
@EnableAsync
//开启定时任务
@EnableScheduling
public class SleuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(SleuthApplication.class, args);
  }


  @Bean
  RestTemplate restTemplate(){
    return new RestTemplate();
  }
}
