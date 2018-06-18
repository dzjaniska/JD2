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
        Product product = new Product(Category.CPU, "cpuModelName", Arrays.asList(
                new Option(Parameter.YEAR, "2018"),
                new Option(Parameter.CAPACITY, "16GB"),
                new Option(Parameter.PRODUCER, "AMD")),
                "imageUrl");

        Product save = productRepository.save(product);
        assertNotNull(save);
    }

    @Test
    public void checkFindByName() {
        Optional<Product> product = productRepository.findFirstByDescription("cpuModelName1");
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
        Product product = productRepository.findFirstByDescription("testProduct").get();
        productRepository.delete(product);

        Iterable<Product> all = productRepository.findAll();
        int size = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList()).size();
        final int expectedSize = 9;
        assertEquals(expectedSize, size);
    }

    @Test
    public void checkUpdate() {
        Optional<Product> product = productRepository.findFirstByDescription("cpuModelName1");
        int updateProduct = productRepository.updateProduct("check", product.get());
        final int expected = 1;
        assertEquals(expected, updateProduct);
    }

    @Test
    public void checkFindDistinctAllByCategoryAndOptionsIn() {
        Option option1 = optionRepository.findFirstByNameAndValue(Parameter.YEAR, "2018");
        Option option2 = optionRepository.findFirstByNameAndValue(Parameter.CAPACITY, "16GB");
        Option option3 = optionRepository.findFirstByNameAndValue(Parameter.CASH, "1");
        List<Product> productList = productRepository.findDistinctAllByCategoryAndOptionsIn(Category.RAM, option1, option2, option3);
        assertEquals("ramModelName1", productList.get(0).getDescription());
    }

    @Test
    public void checkFindAllByCategoryAndOptionsAndShopProductPriceBetween() {
        Option option1 = optionRepository.findFirstByNameAndValue(Parameter.YEAR, "2018");
        List<Product> all = productRepository.findDistinctAllByCategoryAndOptionsAndShopProductPriceBetween(Category.RAM, option1, 100, 400, PageRequest.of(1, 2));
        final int expectedSize = 1;
        assertEquals(expectedSize, all.size());
    }

    @Test
    public void checkFindDistinctAllByCategoryAndOptionsAndShopProductPriceBetween() {
        Option option = optionRepository.findFirstByNameAndValue(Parameter.YEAR, "2017");

        List<Product> products = productRepository.findDistinctAllByCategoryAndOptionsAndShopProductPriceBetween(Category.CPU, option, 230, 320, PageRequest.of(0, 10));
        int size = products.size();
        final int expectedSize = 1;
        assertEquals(expectedSize, size);
    }
}