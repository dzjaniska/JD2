package service;

import entity.Category;
import entity.Option;
import entity.Product;

import java.util.List;

public interface OptionService {

    Option save(Option option);

    List<Option> findAllByProduct(Product product);

    List<Option> findAllByCategory(Category category);

    List<Option> findAll();
}
