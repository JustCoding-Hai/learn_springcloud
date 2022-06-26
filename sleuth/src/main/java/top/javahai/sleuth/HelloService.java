package top.javahai.sleuth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Hai
 * @program: learn_springcloud
 * @description:
 * @create 2022/6/25 - 15:40
 **/
@Service
public class HelloService {

    private static final Log log = LogFactory.getLog(HelloService.class);

    /**
     * 异步任务
     * @return
     */
    @Async
    public String asyncFun() {
        log.info("asyncFun");
        return "asyncFun";
    }


    /**
     * 定时任务
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduleTask() {
        log.info("scheduleTask start");
        asyncFun();
        log.info("scheduleTask end");

    }
}
