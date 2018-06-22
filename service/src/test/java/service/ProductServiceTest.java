package service;

import config.TestConfiguration;
import dto.CatalogDto;
import dto.CatalogPageDto;
import dto.ProductDto;
import dto.ShopProductDto;
import entity.Category;
import entity.Option;
import entity.Parameter;
import entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findById() {
        Product byId = productService.findById(1L);
        assertNotNull(byId);
    }

    @Test
    public void findByIdCatalogItem() {
        CatalogDto byIdCatalogItem = productService.findByIdCatalogItem(1L);
        assertNotNull(byIdCatalogItem);
    }

    @Test
    public void findByIdWithShopsDto() {
        ProductDto byIdWithShopsDto = productService.findByIdWithShopsDto(1L);
        assertNotNull(byIdWithShopsDto);
    }

    @Test
    public void save() {
        Product save = productService.save(new Product(Category.RAM, "as", Arrays.asList(new Option(Parameter.CAPACITY, "sasa")), "asda"));
        assertNotNull(save);
    }

    @Test
    public void findAllByDescriptionContainingIgnoreCase() {
        List<ShopProductDto> cpuModelName1 = productService.findAllByDescriptionContainingIgnoreCase("cpuModelName1");
        assertNotNull(cpuModelName1);
    }

    @Test
    public void findAllByDescriptionContainingIgnoreCaseCatalog() {
        List<CatalogDto> cpuModelName1 = productService.findAllByDescriptionContainingIgnoreCaseCatalog("cpuModelName1");
        assertNotNull(cpuModelName1);
    }

    @Test
    public void findDistinctAllByCategoryAndOptions() {
        CatalogPageDto d = productService.findDistinctAllByCategoryAndOptions(Category.RAM, new Long[]{1L}, "d", PageRequest.of(0, 1));
        assertNotNull(d);
    }
}