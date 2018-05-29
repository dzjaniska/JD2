package dao;

import entity.ReviewShop;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewShopDao extends BaseDao<Long, ReviewShop> {

    private static final ReviewShopDao INSTANCE = new ReviewShopDao();

    public static ReviewShopDao getInstance() {
        return INSTANCE;
    }
}
