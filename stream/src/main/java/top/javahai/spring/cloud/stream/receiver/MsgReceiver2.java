package top.javahai.spring.cloud.stream.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import top.javahai.spring.cloud.stream.channel.MyChannel;
import top.javahai.spring.cloud.stream.channel.MyChannel;

import java.util.Date;

/**
 * @author Hai
 * @program: learn_springcloud
 * @description:
 * @create 2022/1/31 - 21:05
 **/
@EnableBinding(MyChannel.class)
public class MsgReceiver2 {
    public final static Logger LOGGER = LoggerFactory.getLogger(MsgReceiver2.class);

    @StreamListener(MyChannel.HELLO_INPUT)
    public void receive(Object payload) {
        LOGGER.info(new Date() + "MsgReceiver receive2:" + payload);
    }


}
