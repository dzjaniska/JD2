package service;

import dto.CatalogDto;
import dto.CatalogPageDto;
import dto.ProductDto;
import dto.ShopProductDto;
import entity.Category;
import entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product findById(Long id);

    CatalogDto findByIdCatalogItem(Long id);

    ProductDto findByIdWithShopsDto(Long id);

    Product save(Product product);

    CatalogPageDto findDistinctAllByCategory(Category category, Pageable pageable);

    List<ShopProductDto> findAllByDescriptionContainingIgnoreCase(String name);

    List<CatalogDto> findAllByDescriptionContainingIgnoreCaseCatalog(String name);

    CatalogPageDto findDistinctAllByCategoryAndOptionsOrderByPrice(Category category, Long id, Pageable pageable);

}
