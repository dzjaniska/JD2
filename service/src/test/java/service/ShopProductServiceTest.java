package service;

import config.TestConfiguration;
import dto.CartProductDto;
import dto.ShopProductDto;
import entity.Product;
import entity.Shop;
import entity.ShopProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class ShopProductServiceTest {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShopProductService shopProductService;

    @Test
    public void findByProductAndShop() {
        CartProductDto firstByProductAndShop = shopProductService.findFirstByProductAndShop(1L, 1L);
        assertNotNull(firstByProductAndShop);
    }

    @Test
    public void findFirstByProductAndShop() {
        ShopProduct byProductAndShop = shopProductService.findByProductAndShop(1L, 1L);
        assertNotNull(byProductAndShop);
    }

    @Test
    public void findByProductAndShopAndVersion() {
        ShopProduct byProductAndShopAndVersion = shopProductService.findByProductAndShopAndVersion(1L, 2L, 0L);
        assertNotNull(true);
    }

    @Test
    public void save() {
        Shop byId = shopService.findById(1L);
        Product byId1 = productService.findById(1L);
        ShopProduct shopProduct = new ShopProduct(byId, byId1, 5, 5);
        assertNotNull(shopProduct);
    }

    @Test
    public void findAllByShopIdAndQuantityGreaterThan() {
        List<ShopProductDto> allByShopIdAndQuantityGreaterThan = shopProductService.findAllByShopIdAndQuantityGreaterThan(1L);
        assertNotNull(allByShopIdAndQuantityGreaterThan);
    }
}