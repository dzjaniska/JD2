package dao;

import entity.Shop;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShopDao extends BaseDao<Long, Shop> {

    private static final ShopDao INSTANCE = new ShopDao();

    public static ShopDao getInstance() {
        return INSTANCE;
    }
}
