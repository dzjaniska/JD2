package repository;

import entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends UserRepository {

    @Override
    Customer findFirstByLogin(String login);
}
