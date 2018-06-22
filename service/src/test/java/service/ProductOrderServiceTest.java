package service;

import config.TestConfiguration;
import dto.AdminOrderDto;
import entity.Orders;
import entity.ProductOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class ProductOrderServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private UserService userService;

    @Test
    public void save() {
        Orders orders = orderService.save(new Orders(userService.findFirstByLogin("admin1"), LocalDateTime.now(), LocalDate.now(), Arrays.asList(productService.findById(1L))));
        ProductOrder save = productOrderService.save(new ProductOrder(productService.findById(1L), orders, shopService.findById(1L), 1));
        assertNotNull(save);

    }

    @Test
    public void findFirstByProductIdAndOrdersId() {
        ProductOrder firstByProductIdAndOrdersId = productOrderService.findFirstByProductIdAndOrdersId(1L, 1L);
        assertNotNull(true);
    }

    @Test
    public void findAllByShopIdOrderByOrdersDesc() {
        List<AdminOrderDto> allByShopIdOrderByOrdersDesc = productOrderService.findAllByShopIdOrderByOrdersDesc(1L);
        assertNotNull(allByShopIdOrderByOrdersDesc);
    }
}