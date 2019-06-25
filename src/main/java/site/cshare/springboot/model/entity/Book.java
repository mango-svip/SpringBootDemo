package site.cshare.springboot.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book")
public class Book extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`name`", columnDefinition = "varchar(32) not null")
    private String name;

    @Column(name = "`author`", columnDefinition = "varchar(32) not null")
    private String author;

    @Column(name = "`press`", columnDefinition = "varchar(32) not null")
    private String press;

    @Column(name = "`price`", columnDefinition = "int(10)")
    private Integer price;

    @Column(name = "`star`", columnDefinition = "float(10)")
    private Float star;

    @Column(name = "`ratingNum`", columnDefinition = "int(10)")
    private Integer ratingNum;

    @Column(name = "`desc`", columnDefinition = "varchar(1024)")
    private String desc;

}
