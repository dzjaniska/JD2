package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(callSuper = true, exclude = "reviews")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shop", schema = "shop")
public class Shop extends BaseEntity<Long> {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "shop_product", schema = "shop",
            joinColumns = {@JoinColumn(name = "shop_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> products;

    @ManyToMany
    @JoinTable(name = "review_shop", schema = "shop",
            joinColumns = {@JoinColumn(name = "shop_id")},
            inverseJoinColumns = {@JoinColumn(name = "review_id")})
    private Set<ReviewShop> reviews;

    public Shop(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
