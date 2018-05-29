package dao;

import entity.ProductOrder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductOrderDao extends BaseDao<Long, ProductOrder> {

    private static final ProductOrderDao INSTANCE = new ProductOrderDao();

    public static ProductOrderDao getInstance() {
        return INSTANCE;
    }
}
