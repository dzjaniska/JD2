package service;

import entity.Category;
import entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);

    List<Product> findAll();

    List<Product> findDistinctAllByCategory(Category category);

    List<Product> findAllCategoryOrderByMinPrice(Category category);

    Optional<Product> findByName(String name);
}
