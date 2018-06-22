package service;

import config.TestConfiguration;
import entity.ReviewShop;
import entity.Shop;
import entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class ReviewShopServiceTest {

    @Autowired
    private ReviewShopService reviewShopService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    @Test
    public void save() {
        User admin1 = userService.findFirstByLogin("admin1");
        Shop byId = shopService.findById(1L);
        ReviewShop asa = reviewShopService.save(new ReviewShop("asa", 5, LocalDate.now(), admin1, new HashSet<>(Arrays.asList(byId))));
        assertNotNull(asa);
    }
}