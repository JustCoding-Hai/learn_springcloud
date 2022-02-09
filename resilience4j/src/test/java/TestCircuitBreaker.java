import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;

/**
 * @author Hai
 * @date 2020/8/8 - 15:56
 */
public class TestCircuitBreaker {
    @Test
    public void test01(){
      //使用ofDefault调用默认的断路器注册器
      CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();
      CircuitBreakerConfig config = CircuitBreakerConfig.custom()
              //故障率阈值百分比，超过该阈值，断路器就会打开
              .failureRateThreshold(50)
              //断路器保持打开的时间，在到达设置的时间后，断路器会进入到half open状态
              .waitDurationInOpenState(Duration.ofMillis(1000))
              //当断路器处于half open状态时，环形缓冲区的大小
              .ringBufferSizeInHalfOpenState(2)
              .ringBufferSizeInClosedState(2)
              .build();
      //根据配置器来自定义断路器的配置
      CircuitBreakerRegistry r1 = CircuitBreakerRegistry.of(config);
      CircuitBreaker circuitBreaker01 = r1.circuitBreaker("hai");
      CircuitBreaker circuitBreaker02 = r1.circuitBreaker("hai",config);
      CheckedFunction0<String> supplier = CircuitBreaker.decorateCheckedSupplier(circuitBreaker01, () -> "hello resilience");
      Try<String> result = Try.of(supplier).map(v -> v + "hello world");
      System.out.println(result.isSuccess());
      System.out.println(result.get());
      int $s;
    }
}
