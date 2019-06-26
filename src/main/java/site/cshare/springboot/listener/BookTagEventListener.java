package site.cshare.springboot.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import site.cshare.springboot.event.BookTagEvent;
import site.cshare.springboot.model.dto.BookTagDTO;
import site.cshare.springboot.model.entity.BookTag;
import site.cshare.springboot.service.BookTagService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class BookTagEventListener implements ApplicationListener<BookTagEvent> {

    @Autowired
    BookTagService bookTagService;


    @Async
    @Override
    public void onApplicationEvent(BookTagEvent bookTagEvent) {
        List<BookTagDTO> bookTagDTOS = bookTagEvent.getBookTagDTOS();
        List<String> tags = bookTagDTOS.stream().map(BookTagDTO::getTag).distinct().collect(Collectors.toList());
        List<String> existTags = bookTagService.findByTagIn(tags).stream().map(BookTag::getTag).collect(Collectors.toList());
        tags.removeAll(existTags);
        List<BookTag> bookTags = tags.stream().map(this::fromTag).collect(Collectors.toList());
        log.info("Update {} rows", bookTags.size());

        if(bookTags.isEmpty()) {
            return;
        }
        bookTagService.saveAll(bookTags);
    }

    private BookTag fromTag(String tag) {
        BookTag bookTag = new BookTag();
        bookTag.setTag(tag);
        return bookTag;
    }

    private BookTag covertToBookTag(BookTagDTO bookTagDTO) {
        BookTag bookTag = new BookTag();
        bookTag.setTag(bookTagDTO.getTag());
        return bookTag;
    }
}
