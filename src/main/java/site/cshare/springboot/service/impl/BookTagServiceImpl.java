package site.cshare.springboot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cshare.springboot.model.entity.BookTag;
import site.cshare.springboot.repository.BookTagRepository;
import site.cshare.springboot.service.BookTagService;

import java.util.List;

@Service
@Slf4j
public class BookTagServiceImpl implements BookTagService {

    @Autowired
    private BookTagRepository bookTagRepository;

    @Override

    @Transactional(rollbackFor = Exception.class)
    public List<BookTag> saveAll(List<BookTag> bookTags) {

        return bookTagRepository.saveAll(bookTags);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BookTag save(BookTag bookTag) {

        return bookTagRepository.save(bookTag);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookTag> findByTagIn(List<String> tags) {

        return bookTagRepository.findAllByTagIn(tags);
    }

    @Override
    public BookTag findFirstLeastSpider() {
        log.info("Find first book tag least spider");
        return bookTagRepository.findFirstOrderByCurrentPage();
    }
}
