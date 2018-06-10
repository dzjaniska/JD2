package repository;

import entity.Category;
import entity.Option;
import entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findFirstByDescription(String name);

    List<Product> findDistinctAllByCategory(Category category);

    List<Product> findDistinctAllByCategoryAndOptionsIn(Category category, Option... options);

    List<Product> findDistinctAllByCategoryAndOptionsAndShopProductPriceBetween(Category category, Option option, Integer startPrice, Integer endPrice, Pageable pageable);

    @Modifying
    @Query("UPDATE Product p set p.description= :name where p= :product")
    int updateProduct(@Param("name") String name, @Param("product") Product product);

    @Query("select p from Product p inner join p.shopProduct sp where p.category= :category group by p.id order by min(sp.price)")
    List<Product> findAllCategoryOrderByMinPrice(@Param("category") Category category);

    //    List<Product> findDistinctAllByCategoryAndOptionsIn(@Param("ids") Option ids);
    //    @Query("select distinct p from Product p where p.category and p.options= :ids")
    //    List<Product> findAllByCategoryAndOptionsAndShopProductPriceGreaterThan1(Category cate
}
