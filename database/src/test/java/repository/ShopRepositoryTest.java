package repository;

import config.TestConfiguration;
import entity.Shop;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import util.DatabaseHelper;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class ShopRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private ShopRepository shopRepository;


    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void checkSave() {
        Shop logo = new Shop("111", "123", "logo", 123222L);
        Shop save = shopRepository.save(logo);

        assertNotNull(save);
    }
}