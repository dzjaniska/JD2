package service;

import dto.AdminOrderDto;
import entity.ProductOrder;

import java.util.List;

public interface ProductOrderService {

    ProductOrder save(ProductOrder user);

    ProductOrder findFirstByProductIdAndOrdersId(Long productId, Long orderId);

    List<AdminOrderDto> findAllByShopIdOrderByOrdersDesc(Long id);
}
