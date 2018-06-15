package repository;

import entity.Product;
import entity.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Long> {

    List<Shop> findAllByProducts(Product product);
}
