package repository;

import config.TestConfiguration;
import dto.AdminOrderDto;
import entity.ProductOrder;
import entity.ReviewProduct;
import entity.ReviewShop;
import entity.Shop;
import entity.ShopProduct;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import util.DatabaseHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class ProductOrderRepositoryTest {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private DatabaseHelper databaseHelper;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void findFirstByProductIdAndOrdersId() {
        new ProductOrder().builder()
                .orders(null)
                .product(null)
                .quantity(null)
                .shop(null)
                .build();
        new ReviewShop().builder()
                .shop(null)
                .date(null)
                .rating(null)
                .text(null)
                .user(null)
                .build();
        new ReviewProduct().builder()
                .product(null)
                .date(null)
                .rating(null)
                .text(null)
                .user(null)
                .build();
        new ShopProduct().builder()
                .quantity(null)
                .price(null)
                .product(null)
                .shop(null)
                .version(null)
                .build();
        AdminOrderDto adminOrderDto = new AdminOrderDto(1L, 1L, "as", "as", "as", 1L, "sa", "as", 2, LocalDateTime.now(), LocalDate.now());
        adminOrderDto.getAddress();
        adminOrderDto.getDeliveryTime();
        adminOrderDto.getOrderId();
        adminOrderDto.getOrderTime();
        adminOrderDto.getProductId();
        adminOrderDto.getProductImage();
        adminOrderDto.getQuantity();
        adminOrderDto.getUserId();
        adminOrderDto.getUserName();
        adminOrderDto.getUserSecondName();


        ProductOrder order = productOrderRepository.findFirstByProductIdAndOrdersId(1L, 1L);
        assertNotNull(order);
    }

    @Test
    public void findAllByShopIdOrderByOrdersDesc() {
        Shop shop = shopRepository.findFirstByName("21vek");
        List<ProductOrder> order = productOrderRepository.findAllByShopIdOrderByOrdersDesc(shop.getId());
        final int expectedSize = 3;
        assertEquals(expectedSize, order.size());
    }
}