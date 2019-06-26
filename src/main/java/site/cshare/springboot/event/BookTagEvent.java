package site.cshare.springboot.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;
import site.cshare.springboot.model.dto.BookTagDTO;

import java.util.List;

@Data
public class BookTagEvent extends ApplicationEvent {

    private List<BookTagDTO> bookTagDTOS;

    public BookTagEvent(Object source, List<BookTagDTO> bookTagDTOS) {
        super(source);
        this.bookTagDTOS = bookTagDTOS;
    }

}
