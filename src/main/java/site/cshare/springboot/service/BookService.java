package site.cshare.springboot.service;

import site.cshare.springboot.model.entity.Book;

import java.util.List;

public interface BookService {

    Book save(Book book);

    List<Book> saveAll(List<Book> books);
}
