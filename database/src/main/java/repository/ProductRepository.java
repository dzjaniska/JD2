package repository;

import entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {

}
