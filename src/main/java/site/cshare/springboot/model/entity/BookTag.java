package site.cshare.springboot.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "book_tag")
@Entity
public class BookTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`tag`", columnDefinition = "varchar(32) not null")
    private String tag;

    @Column(name = "current_page", columnDefinition = "int(10) not null")
    private Integer currentPage;

    @Column(name = "has_next_page", columnDefinition = "TINYINT default 1")
    private Integer hasNextPage;


    @Override
    protected void prePersist() {
        super.prePersist();
        if (currentPage == null) {
            currentPage = 0;
        }

        if(hasNextPage == null) {
            hasNextPage = 1;
        }
    }
}
