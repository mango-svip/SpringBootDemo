package site.cshare.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.cshare.springboot.model.entity.BookTag;

import java.util.List;

public interface BookTagRepository extends JpaRepository<BookTag, Long> {

    List<BookTag> findAllByTagIn(List<String> tags);

    @Query(value = "select * from book_tag order by current_page, id limit 1", nativeQuery = true)
    BookTag findFirstOrderByCurrentPage();
}
