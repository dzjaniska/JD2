package dao;

import dao.interfaces.ProductOrderDao;
import entity.ProductOrder;
import org.springframework.stereotype.Repository;

@Repository
public class ProductOrderDaoImpl extends BaseDaoImpl<Long, ProductOrder> implements ProductOrderDao {
}
