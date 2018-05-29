package dao;

import entity.ShopProduct;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShopProductDao extends BaseDao<Long, ShopProduct> {

    private static final ShopProductDao INSTANCE = new ShopProductDao();

    public static ShopProductDao getInstance() {
        return INSTANCE;
    }
}
