package repository;

import entity.ShopProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopProductRepository extends CrudRepository<ShopProduct, Long> {

    ShopProduct findFirstByProductIdAndShopId(Long productId, Long shopId);
}
