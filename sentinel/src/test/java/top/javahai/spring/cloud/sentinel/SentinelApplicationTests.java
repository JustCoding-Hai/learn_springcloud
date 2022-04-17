package top.javahai.spring.cloud.sentinel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@SpringBootTest
class SentinelApplicationTests {

    @Test
    void contextLoads() {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 15; i++) {
            String strResponse = restTemplate.getForObject("http://localhost:8083/test", String.class);
            System.out.println(strResponse + ":" + LocalDateTime.now());

        }
    }

}
