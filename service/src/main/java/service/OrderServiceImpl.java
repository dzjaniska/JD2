package service;

import dto.UserOrderDto;
import dto.UserProductDto;
import entity.Orders;
import entity.ProductOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ProductOrderService productOrderService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductOrderService productOrderService) {
        this.orderRepository = orderRepository;
        this.productOrderService = productOrderService;
    }

    @Override
    public Orders save(Orders order) {
        return orderRepository.save(order);
    }

    @Override
    public List<UserOrderDto> findAllByUserId(Long id) {
        List<UserOrderDto> dtos = new ArrayList<>();

        List<Orders> orders = orderRepository.findAllByUserId(id);
        orders.stream().forEach(it -> dtos.add(new UserOrderDto().builder()
                .orderId(it.getId())
                .orderTime(it.getOrderTime())
                .products(getProductList(it))
                .build()
        ));

        return dtos;
    }

    private List<UserProductDto> getProductList(Orders order) {
        List<UserProductDto> productDtos = new ArrayList<>();

        order.getProducts().forEach(it -> {
            ProductOrder productOrder = productOrderService.findFirstByProductIdAndOrdersId(it.getId(), order.getId());
            productDtos.add(new UserProductDto().builder()
                    .productId(it.getId())
                    .productName(it.getDescription())
                    .productImage(it.getImage())
                    .shopId(productOrder.getShop().getId())
                    .shopLogo(productOrder.getShop().getLogo())
                    .quantity(productOrder.getQuantity())
                    .build());
        });

        return productDtos;
    }
}
