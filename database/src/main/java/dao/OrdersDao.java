package dao;

import entity.Orders;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrdersDao extends BaseDao<Long, Orders> {

    private static final OrdersDao INSTANCE = new OrdersDao();

    public static OrdersDao getInstance() {
        return INSTANCE;
    }
}
