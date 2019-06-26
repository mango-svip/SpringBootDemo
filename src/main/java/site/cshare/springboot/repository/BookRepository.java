package site.cshare.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.cshare.springboot.model.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
