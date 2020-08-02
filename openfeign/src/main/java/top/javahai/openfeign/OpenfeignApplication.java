package top.javahai.openfeign;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author hai
 * @create 2020-07-29 16:07
 */
@SpringBootApplication
@EnableFeignClients
public class OpenfeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenfeignApplication.class, args);
	}

	@Bean
	Logger.Level loggerLevel(){
		return Logger.Level.FULL;
	}

}
