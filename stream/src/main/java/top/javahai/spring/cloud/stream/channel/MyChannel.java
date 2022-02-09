package top.javahai.spring.cloud.stream.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Hai
 * @program: learn_springcloud
 * @description:
 * @create 2022/1/31 - 20:41
 **/
public interface MyChannel {
    String HELLO_INPUT = "hello-input";
    String HELLO_OUTPUT = "hello-output";

    @Output(HELLO_OUTPUT)
    MessageChannel output();

    @Input(HELLO_INPUT)
    SubscribableChannel input();
}
