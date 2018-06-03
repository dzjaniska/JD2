package dao;

import dao.interfaces.OrdersDao;
import entity.Orders;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersDaoImpl extends BaseDaoImpl<Long, Orders> implements OrdersDao {
}
