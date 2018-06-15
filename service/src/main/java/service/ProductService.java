package service;

import dto.ProductDto;
import entity.Category;
import entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product findById(Long id);

    Product findByIdWithShops(Long id);

    ProductDto findByIdWithShopsDto(Long id);

    Product save(Product product);

    List<Product> findAll();

    List<Product> findDistinctAllByCategory(Category category);

    List<Product> findAllCategoryOrderByMinPrice(Category category);

    Optional<Product> findByName(String name);
}
