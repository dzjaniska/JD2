package service;

import config.TestConfiguration;
import entity.Product;
import entity.ReviewProduct;
import entity.ReviewShop;
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
public class ReviewProductServiceTest {

    @Autowired
    private ReviewProductService reviewProductService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Test
    public void save() {
        User admin1 = userService.findFirstByLogin("admin1");
        Product byId = productService.findById(1L);
        ReviewProduct asa = reviewProductService.save(new ReviewProduct("asa", 5, LocalDate.now(), admin1, new HashSet<>(Arrays.asList(byId))));
        assertNotNull(asa);
    }
}