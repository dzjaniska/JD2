package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @OneToMany(mappedBy = "product")
    private Set<Option> options;

    @ManyToMany(mappedBy = "product")
    private Set<ReviewProduct> reviews;

    public Product(Category category, String description, String image) {
        this.category = category;
        this.description = description;
        this.image = image;
    }
}
