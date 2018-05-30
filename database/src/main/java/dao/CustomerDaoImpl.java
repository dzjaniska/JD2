package dao;

import dao.interfaces.CustomerDao;
import entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl extends BaseDaoImpl<Long, Customer> implements CustomerDao {

}
