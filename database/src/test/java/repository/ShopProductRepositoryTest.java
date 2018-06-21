package repository;

import config.TestConfiguration;
import entity.Product;
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

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class ShopProductRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopProductRepository shopProductRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void findFirstByProductIdAndShopId() {
        Shop shop = shopRepository.findFirstByName("21vek");
        Product product = productRepository.findFirstByDescription("cpuModelName1").get();
        ShopProduct id = shopProductRepository.findFirstByProductIdAndShopId(shop.getId(), product.getId());
        assertNotNull(id);
    }

    @Test
    public void findAllByShopIdAndQuantityGreaterThan() {
        Shop shop = shopRepository.findFirstByName("21vek");
        List<ShopProduct> list = shopProductRepository.findAllByShopIdAndQuantityGreaterThan(shop.getId(), 0);
        assertNotNull(list);
    }
}