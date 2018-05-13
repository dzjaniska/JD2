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
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product", schema = "shop")
public class Product extends BaseEntity<Long> {

    @Column(name = "category", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "description", nullable = false, unique = false)
    private String description;

    @Column(name = "image", nullable = false, unique = false)
    private String image;
}
