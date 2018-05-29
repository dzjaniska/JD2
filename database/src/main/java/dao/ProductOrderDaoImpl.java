package dao;

import entity.ProductOrder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductOrderDaoImpl extends BaseBaseDao<Long, ProductOrder> {

    private static final ProductOrderDaoImpl INSTANCE = new ProductOrderDaoImpl();

    public static ProductOrderDaoImpl getInstance() {
        return INSTANCE;
    }
}
