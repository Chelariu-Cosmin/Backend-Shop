package backend.springboot.online.shop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ArticleDto {

    private String name;
    private String description;
    private BigDecimal price;
    private String photo;
}
