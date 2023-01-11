package backend.springboot.online.shop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "articles")
public class Article extends BaseId {


    @Column(length = 128, nullable = false, name = "article_name")
    @NonNull
    private String name;

    @Column(length = 512, nullable = false, name = "description")
    @NonNull
    private String description;

    @Column(length = 16, nullable = false, name = "price")
    @NonNull
    private BigDecimal price;

    @Column(length = 64, name = "photo_url")
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

}

