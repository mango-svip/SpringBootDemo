package site.cshare.springboot.spider;


import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import site.cshare.springboot.config.DouBanConfig;
import site.cshare.springboot.event.BookTagEvent;
import site.cshare.springboot.model.dto.BookTagDTO;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Component("bookTagSpiderTask")
@Slf4j
public class BookTagSpiderTask implements SpiderTask {

    @Autowired
    DouBanConfig douBanConfig;

    @Autowired
    ApplicationContext applicationContext;


    @Override
    public void handler() throws IOException {

        Document document = Jsoup.parse(new URL(douBanConfig.getHotUrl()), 2000);
        List<BookTagDTO> bookTagDTOList = new ArrayList<>();
        document.body().select("table.tagCol").select("td a").forEach(e -> {
            log.info("text = {}", e.text());
            bookTagDTOList.add(new BookTagDTO().setTag(e.text()));
        });

        applicationContext.publishEvent(new BookTagEvent(this, bookTagDTOList));
    }
}
