package site.cshare.springboot.service;

import site.cshare.springboot.model.entity.BookTag;

import java.util.List;

public interface BookTagService {

    List<BookTag> saveAll(List<BookTag> bookTags);

    BookTag save(BookTag bookTag);

    List<BookTag> findByTagIn(List<String> tags);

    BookTag findFirstLeastSpider();
}
