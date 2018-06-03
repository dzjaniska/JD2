package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(callSuper = true, exclude = {"user", "shop"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review", schema = "shop")
public class ReviewProduct extends BaseEntity<Long> {

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "review_product", schema = "shop",
            joinColumns = {@JoinColumn(name = "review_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> product;
}
