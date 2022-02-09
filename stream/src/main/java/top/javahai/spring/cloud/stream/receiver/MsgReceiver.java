package top.javahai.spring.cloud.stream.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author Hai
 * @program: learn_springcloud
 * @description:
 * @create 2022/1/31 - 20:28
 **/
//@EnableBinding 表示绑定 Sink 消息通道
@EnableBinding(Sink.class)
public class MsgReceiver {
    public final static Logger LOGGER = LoggerFactory.getLogger(MsgReceiver.class);

    @StreamListener(Sink.INPUT)
    public void receive(Object payload) {
        LOGGER.info(" MsgReceiver Received:" + payload);
    }
}
