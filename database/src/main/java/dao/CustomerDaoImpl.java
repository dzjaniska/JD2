package dao;

import entity.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDaoImpl extends BaseBaseDao<Long, Customer> {

    private static final CustomerDaoImpl INSTANCE = new CustomerDaoImpl();

    public static CustomerDaoImpl getInstance() {
        return INSTANCE;
    }
}
