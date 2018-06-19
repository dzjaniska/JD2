package repository;

import entity.ProductOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends CrudRepository<ProductOrder, Long> {

    ProductOrder findFirstByProductIdAndOrdersId(Long productId, Long orderId);

    List<ProductOrder> findAllByShopIdOrderByOrdersDesc(Long id);
}
