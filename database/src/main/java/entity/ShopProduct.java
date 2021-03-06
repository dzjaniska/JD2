package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Getter
@Setter
@ToString(callSuper = true, exclude = {"product", "shop"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "shop_product", schema = "shop")
public class ShopProduct extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Version
    @Column(name = "version")
    private Long version;

    public ShopProduct(Shop shop, Product product, Integer quantity, Integer price) {
        this.shop = shop;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
}
