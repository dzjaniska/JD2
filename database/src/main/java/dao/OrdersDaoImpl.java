package dao;

import entity.Orders;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrdersDaoImpl extends BaseBaseDao<Long, Orders> {

    private static final OrdersDaoImpl INSTANCE = new OrdersDaoImpl();

    public static OrdersDaoImpl getInstance() {
        return INSTANCE;
    }
}
