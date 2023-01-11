package backend.springboot.online.shop.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "order_details")
public class OrderDetails extends BaseId {

    @Column(length = 12, nullable = false)
    @NotNull
    private double price;

    @Column(length = 12, nullable = false)
    @NotNull
    private String currency;

    @Column(length = 12, nullable = false)
    @NotNull
    private String method;

    @Column(length = 12, nullable = false)
    @NotNull
    private String intent;

    @Column(length = 1200, nullable = false)
    private String description;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    @ToString.Exclude
//    private Order order;
}
