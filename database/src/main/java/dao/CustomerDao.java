package dao;

import entity.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDao extends BaseDao<Long, Customer> {

    private static final CustomerDao INSTANCE = new CustomerDao();

    public static CustomerDao getInstance() {
        return INSTANCE;
    }
}
