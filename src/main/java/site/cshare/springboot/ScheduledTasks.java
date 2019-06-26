package site.cshare.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import site.cshare.springboot.spider.SpiderTask;

import java.io.IOException;

@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    @Qualifier("bookSpiderTask")
    private SpiderTask bookSpiderTask;


    @Autowired
    @Qualifier("bookTagSpiderTask")
    private SpiderTask bookTagSpiderTask;

    @Scheduled(cron = "0 0/20 * * * ?")
    public void bookTagspider() throws IOException {
        log.info("Begin book tag spider scheduled");
        bookTagSpiderTask.handler();
        log.info("End book tag spider scheduled");
    }



    @Scheduled(cron = "0 0/1 * * * ?")
    public void bookSpider() throws IOException {
        log.info("Begin book spider scheduled ");
        bookSpiderTask.handler();
        log.info("End book spider scheduled ");
    }
}
