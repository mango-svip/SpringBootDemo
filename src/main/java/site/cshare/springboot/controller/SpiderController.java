package site.cshare.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cshare.springboot.spider.SpiderTask;

import java.io.IOException;

@RestController
@RequestMapping("/spider")
public class SpiderController {

    @Autowired
    @Qualifier("bookTagSpiderTask")
    SpiderTask bookTagSpiderTask;

    @Qualifier("bookSpiderTask")
    @Autowired
    SpiderTask bookSpiderTask;


    @GetMapping("/tag")
    public ResponseEntity spiderTag() throws IOException {
        bookTagSpiderTask.handler();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/book")
    public ResponseEntity spiderBook() throws IOException {

        bookSpiderTask.handler();

        return ResponseEntity.noContent().build();
    }
}
