package repository;

import entity.Category;
import entity.Option;
import entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findFirstByDescription(String name);

    List<Product> findDistinctAllByCategoryAndOptionsIn(Category category, Option... options);

    List<Product> findDistinctAllByCategoryAndOptionsAndShopProductPriceBetween(Category category, Option option, Integer startPrice, Integer endPrice, Pageable pageable);

    @Modifying
    @Query("UPDATE Product p set p.description= :name where p= :product")
    int updateProduct(@Param("name") String name, @Param("product") Product product);

//    List<Product> findAllByCategoryAndOptionsAndShopProductPriceGreaterThan1(Category cate
//    @Query("select distinct p from Product p where p.category and p.options= :ids")
//    List<Product> findDistinctAllByCategoryAndOptionsIn(@Param("ids") Option ids);
}
