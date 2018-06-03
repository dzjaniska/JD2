package repository;

import entity.Category;
import entity.Option;
import entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAllByCategoryAndOptionsIn(Category category, Option... options);
}
