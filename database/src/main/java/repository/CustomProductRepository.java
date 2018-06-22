package repository;

import dto.OptionDto;
import entity.Category;
import entity.Parameter;
import entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CustomProductRepository {

    List<Product> findDistinctAllByCategoryAndOptionsCustom(Category category, List<List<Long>> id, String sort, Pageable pageable);
}

