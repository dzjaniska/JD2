package repository;

import entity.Category;
import entity.Option;
import entity.Product;
import org.springframework.data.domain.Page;
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

//    Page<Product> findDistinctAllByCategory(Category category, Pageable pageable);

    @Query("select p from Product p inner join p.shopProduct sp where p.category = :category group by p.id having sum(sp.quantity) >0")
    List<Product> findDistinctAllByCategory(@Param("category") Category category);

    @Query("select p from Product p inner join p.options o inner join p.shopProduct sp where p.category = :category and o.id= :id group by p.id having sum(sp.quantity) >0  order by min(sp.price)")
    List<Product> findDistinctAllByCategoryAndOptionsOrderByPrice(@Param("category") Category category, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Product p set p.description= :name where p= :product")
    int updateProduct(@Param("name") String name, @Param("product") Product product);

    List<Product> findAllByDescriptionContainingIgnoreCase(String name);
}
