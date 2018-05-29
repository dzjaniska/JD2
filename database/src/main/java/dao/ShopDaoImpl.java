package dao;

import entity.Shop;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShopDaoImpl extends BaseBaseDao<Long, Shop> {

    private static final ShopDaoImpl INSTANCE = new ShopDaoImpl();

    public static ShopDaoImpl getInstance() {
        return INSTANCE;
    }
}
