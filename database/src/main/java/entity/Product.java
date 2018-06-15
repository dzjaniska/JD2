package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(callSuper = true, exclude = "reviews")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product", schema = "shop")
public class Product extends BaseEntity<Long> {

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image", nullable = false)
    private String image;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "option_product", schema = "shop",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "option_id")})
    private List<Option> options;

    @ManyToMany(mappedBy = "product")
    private Set<ReviewProduct> reviews;

    @OneToMany(mappedBy = "product")
    private Set<ShopProduct> shopProduct;

    public Product(Category category, String description, List<Option> options, String image) {
        this.category = category;
        this.description = description;
        this.options = options;
        this.image = image;
    }
}
