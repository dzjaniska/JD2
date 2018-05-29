package dao;

import entity.ReviewShop;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewShopDaoImpl extends BaseBaseDao<Long, ReviewShop> {

    private static final ReviewShopDaoImpl INSTANCE = new ReviewShopDaoImpl();

    public static ReviewShopDaoImpl getInstance() {
        return INSTANCE;
    }
}
