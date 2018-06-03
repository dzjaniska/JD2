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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import util.DatabaseHelper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class ProductRepositoryTest {

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
        Product product = new Product(Category.CPU, "cpuModelName", new HashSet<Option>(Arrays.asList(
                new Option(Parameter.YEAR, "2018"),
                new Option(Parameter.CAPACITY, "16GB"),
                new Option(Parameter.PRODUCER, "AMD"))),
                "imageUrl");

        Product save = productRepository.save(product);
        assertNotNull(save);
    }

    @Test
    public void checkFind() {
        Optional<Product> product = productRepository.findById(29L);
        assertNotNull(product.get());
    }

    @Test
    public void checkFindAll() {
        Iterable<Product> all = productRepository.findAll();
        int size = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList()).size();
        final int expectedSize = 10;
        assertEquals(expectedSize, size);
    }

    @Test
    public void checkDelete() {
        Optional<Product> product = productRepository.findById(38L);
        productRepository.delete(product.get());

        Iterable<Product> all = productRepository.findAll();
        int size = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList()).size();
        final int expectedSize = 9;
        assertEquals(expectedSize, size);
    }

    @Test
    public void checkUpdate() {
        Product product = productRepository.findById(38L).get();
        int updateProduct = productRepository.updateProduct("check", product);
        final int expected = 1;
        assertEquals(expected, updateProduct);
    }

    @Test
    public void checkFindDistinctAllByCategoryAndOptionsIn() {
        Option option1 = optionRepository.findByNameAndValue(Parameter.YEAR, "2018");
        Option option2 = optionRepository.findByNameAndValue(Parameter.CAPACITY, "16GB");
        Option option3 = optionRepository.findByNameAndValue(Parameter.CASH, "1");
        List<Product> productList = productRepository.findDistinctAllByCategoryAndOptionsIn(Category.RAM, option1, option2);
        assertEquals("ramModelName1", productList.get(0).getDescription());
    }

    @Test
    public void checkFindAllByCategoryAndOptionsAndShopProductPriceBetween() {
        Option option1 = optionRepository.findByNameAndValue(Parameter.YEAR, "2018");
        List<Product> all = productRepository.findDistinctAllByCategoryAndOptionsAndShopProductPriceBetween(Category.RAM, option1, 100, 400, PageRequest.of(1, 2));
        final int expectedSize = 1;
        assertEquals(expectedSize, all.size());
    }
}