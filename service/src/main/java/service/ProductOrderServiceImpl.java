package service;

import dto.AdminOrderDto;
import entity.Customer;
import entity.ProductOrder;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ProductOrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductOrderServiceImpl implements ProductOrderService {

    private ProductOrderRepository productOrderRepository;

    @Autowired
    public ProductOrderServiceImpl(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    @Override
    public ProductOrder save(ProductOrder productOrder) {
        return productOrderRepository.save(productOrder);
    }

    @Override
    public ProductOrder findFirstByProductIdAndOrdersId(Long productId, Long orderId) {
        return productOrderRepository.findFirstByProductIdAndOrdersId(productId, orderId);
    }

    @Override
    public List<AdminOrderDto> findAllByShopIdOrderByOrdersDesc(Long id) {
        List<AdminOrderDto> shopOrders = new ArrayList<>();

        List<ProductOrder> orders = productOrderRepository.findAllByShopIdOrderByOrdersDesc(id);
        orders.forEach(it -> {
            User user = it.getOrders().getUser();
            Customer customer = (Customer) user;
            shopOrders.add(new AdminOrderDto().builder()
                    .orderId(it.getOrders().getId())
                    .userId(user.getId())
                    .userName(user.getUserInfo().getName())
                    .userSecondName(user.getUserInfo().getSecondName())
                    .address(customer.getAddress())
                    .productId(it.getProduct().getId())
                    .productName(it.getProduct().getDescription())
                    .productImage(it.getProduct().getImage())
                    .orderTime(it.getOrders().getOrderTime())
                    .deliveryTime(it.getOrders().getDeliveryTime())
                    .quantity(it.getQuantity())
                    .build());
        });

        return shopOrders;
    }

}
