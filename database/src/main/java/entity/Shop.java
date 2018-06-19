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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(callSuper = true, exclude = {"reviews", "products"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shop", schema = "shop")
public class Shop extends BaseEntity<Long> {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "logo", nullable = false)
    private String logo;

    @Column(name = "regNumber", nullable = false, unique = true)
    private Long regNumber;

    @OneToMany(mappedBy = "shop")
    private Set<ShopProduct> products;

    @ManyToMany
    @JoinTable(name = "review_shop", schema = "shop",
            joinColumns = {@JoinColumn(name = "shop_id")},
            inverseJoinColumns = {@JoinColumn(name = "review_id")})
    private Set<ReviewShop> reviews;

    public Shop(String name, String description, String logo, Long regNumber) {
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.regNumber = regNumber;
    }
}
