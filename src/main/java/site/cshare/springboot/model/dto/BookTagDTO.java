package site.cshare.springboot.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BookTagDTO {

    private String tag;
}
