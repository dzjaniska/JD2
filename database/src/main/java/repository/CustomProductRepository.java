package repository;

import entity.Category;
import entity.Product;

import java.util.List;

public interface CustomProductRepository {

    List<Product> findDistinctAllByCategoryAndOptionsCustom(Category category, Long[] id);
}

