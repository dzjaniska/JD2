package service;

import dto.CatalogDto;
import dto.CatalogPageDto;
import dto.OptionDto;
import dto.ProductDto;
import dto.ShopProductDto;
import entity.Category;
import entity.Parameter;
import entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Product findById(Long id);

    CatalogDto findByIdCatalogItem(Long id);

    ProductDto findByIdWithShopsDto(Long id);

    Product save(Product product);

    List<ShopProductDto> findAllByDescriptionContainingIgnoreCase(String name);

    List<CatalogDto> findAllByDescriptionContainingIgnoreCaseCatalog(String name);

    CatalogPageDto findDistinctAllByCategoryAndOptions(Category category, Long[] ids, String sort, Pageable pageable);
}
