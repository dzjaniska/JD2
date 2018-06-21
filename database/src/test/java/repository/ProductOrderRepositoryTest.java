package repository;

import config.TestConfiguration;
import entity.ProductOrder;
import entity.Shop;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import util.DatabaseHelper;

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