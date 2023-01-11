package backend.springboot.online.shop.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseId {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<Article> articles = new ArrayList<> ();

    @Column(length = 12, nullable = false)
    @NotNull
    @Positive
    private BigDecimal cashAmount;

    @Column(length = 12, nullable = false)
    @NotNull
    private Integer units;

//    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY, optional = false)
//    private OrderDetails details;

}

