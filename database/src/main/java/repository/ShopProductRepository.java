package repository;

import entity.ShopProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopProductRepository extends CrudRepository<ShopProduct, Long> {

    ShopProduct findFirstByProductIdAndShopId(Long productId, Long shopId);

    List<ShopProduct> findAllByShopIdAndQuantityGreaterThan(Long id, Integer quantity);
}
