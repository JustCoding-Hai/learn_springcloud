package top.javahai.spring.cloud.stream.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.javahai.spring.cloud.stream.channel.MyChannel;
import top.javahai.spring.cloud.stream.receiver.MsgReceiver2;

import java.util.Date;

/**
 * @author Hai
 * @program: learn_springcloud
 * @description:
 * @create 2022/1/31 - 21:07
 **/
@RestController
public class HelloController {

    public final static Logger LOGGER = LoggerFactory.getLogger(HelloController.class);


    @Autowired
    MyChannel myChannel;

    @GetMapping("/hello")
    public void hello(){
        myChannel.output().send(MessageBuilder.withPayload("hello spring cloud stream!").build());
    }


    @GetMapping("/delay-hello")
    public void delayHello(){
        LOGGER.info("send msg:" + new Date());
        myChannel.output().send(MessageBuilder.withPayload("hello spring cloud stream!").setHeader("x-delay", 5000).build());
    }
}
