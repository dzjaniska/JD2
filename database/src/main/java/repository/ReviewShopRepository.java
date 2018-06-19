package repository;

import entity.ReviewShop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewShopRepository extends CrudRepository<ReviewShop, Long> {
}
