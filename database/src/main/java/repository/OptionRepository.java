package repository;

import entity.Category;
import entity.Option;
import entity.Parameter;
import entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long> {

    Option findFirstByNameAndValue(Parameter parameter, String value);

    List<Option> findAllByProducts(Product product);

    @Query("select o from Product p inner join p.options o where p.category= :category")
    List<Option> findAllByCategory(@Param("category") Category category);
}
