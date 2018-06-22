package service;

import config.TestConfiguration;
import dto.UserOrderDto;
import entity.Orders;
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
public class OrderServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Test
    public void save() {
        Orders orders = orderService.save(new Orders(userService.findFirstByLogin("admin1"), LocalDateTime.now(), LocalDate.now(), Arrays.asList(productService.findById(1L))));
        assertNotNull(orders);
    }

    @Test
    public void findAllByUserId() {
        List<UserOrderDto> allByUserId = orderService.findAllByUserId(1L);
        assertNotNull(allByUserId);
    }
}