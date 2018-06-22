package service;

import config.TestConfiguration;
import entity.Category;
import entity.Option;
import entity.Parameter;
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
public class OptionServiceTest {

    @Autowired
    private OptionService optionService;

    @Autowired
    private ProductService productService;

    @Test
    public void save() {
        Option save = optionService.save(new Option(Parameter.CAPACITY, "121"));
        assertNotNull(save);
    }

    @Test
    public void findDistinctByCategory() {
        List<Option> distinctByCategory = optionService.findDistinctByCategory(Category.RAM);
        assertNotNull(distinctByCategory);
    }
}