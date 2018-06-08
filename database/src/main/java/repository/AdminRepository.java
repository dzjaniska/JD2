package repository;

import entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends UserRepository {

    @Override
    Admin findFirstByLogin(String login);
}
