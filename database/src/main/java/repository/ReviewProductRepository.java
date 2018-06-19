package repository;

import entity.ReviewProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewProductRepository extends CrudRepository<ReviewProduct, Long> {
}
