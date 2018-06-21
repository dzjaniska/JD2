package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true, exclude = "products")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders", schema = "shop")
public class Orders extends BaseEntity<Long> {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "delivery_time")
    private LocalDate deliveryTime;

    @ManyToMany
    @JoinTable(name = "product_order", schema = "shop",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products;

    public Orders(User user, LocalDateTime orderTime, LocalDate deliveryTime) {
        this.user = user;
        this.orderTime = orderTime;
        this.deliveryTime = deliveryTime;
    }
}
