package dao;

import entity.ShopProduct;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShopProductDaoImpl extends BaseBaseDao<Long, ShopProduct> {

    private static final ShopProductDaoImpl INSTANCE = new ShopProductDaoImpl();

    public static ShopProductDaoImpl getInstance() {
        return INSTANCE;
    }
}
