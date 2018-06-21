package repository;

import config.TestConfiguration;
import entity.Category;
import entity.Option;
import entity.Parameter;
import entity.Product;
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
public class OptionRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void checkSave() {
        Product product = productRepository.findFirstByDescription("cpuModelName1").get();
        List<Option> options = optionRepository.findAllByProducts(product);
        final int expectedSize = 3;
        assertEquals(expectedSize, options.size());
    }

    @Test
    public void findFirstByNameAndValue() {
        Option option = optionRepository.findFirstByNameAndValue(Parameter.YEAR, "2018");
        assertNotNull(option);
    }

    @Test
    public void findAllByProducts() {
        Product product = productRepository.findFirstByDescription("cpuModelName1").get();
        List<Option> options = optionRepository.findAllByProducts(product);
        final int expectedSize = 3;
        assertEquals(expectedSize, options.size());

    }

    @Test
    public void findAllByCategory() {
        List<Option> options = optionRepository.findAllByCategory(Category.RAM);
        final int expectedSize = 15;
        assertEquals(expectedSize, options.size());
    }
}