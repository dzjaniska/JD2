package repository;

import entity.Category;
import entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomProductRepository {

    List<Product> findDistinctAllByCategoryAndOptionsCustom(Category category, List<List<Long>> id, String sort, Pageable pageable);
}

