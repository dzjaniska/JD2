package service;

import config.TestConfiguration;
import dto.ShopPageDto;
import entity.Shop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class ShopServiceTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void save() {
        Shop save = shopService.save(new Shop("a", "a", "a", 1333113L));
        assertNotNull(save);
    }

    @Test
    public void findById() {
        Shop byId = shopService.findById(1L);
        assertNotNull(byId);

    }

    @Test
    public void findByIdDto() {
        ShopPageDto byIdDto = shopService.findByIdDto(1L);
        assertNotNull(byIdDto);
    }
}