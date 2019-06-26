package site.cshare.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cshare.springboot.model.entity.Book;
import site.cshare.springboot.repository.BookRepository;
import site.cshare.springboot.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Book> saveAll(List<Book> books) {
        return bookRepository.saveAll(books);
    }
}
