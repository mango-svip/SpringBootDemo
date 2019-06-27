package site.cshare.springboot.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book")
@Accessors(chain = true)
public class Book extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`name`", columnDefinition = "varchar(128) not null")
    private String name;

    @Column(name = "`author`", columnDefinition = "varchar(256) not null")
    private String author;

    @Column(name = "`press`", columnDefinition = "varchar(256) not null")
    private String press;

    @Column(name = "`star`", columnDefinition = "float(10)")
    private Float star;

    @Column(name = "`ratingNum`", columnDefinition = "int(10)")
    private Integer ratingNum;

    @Column(name = "img", columnDefinition = "varchar(256)")
    private String img;

    @Column(name = "`desc`", columnDefinition = "varchar(1024)")
    private String desc;

    @Column(name = "tag", columnDefinition = "varchar(32)")
    private String tag;

}
