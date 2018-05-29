package dao;

import entity.ReviewProduct;
import entity.ReviewShop;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewProductDao extends BaseDao<Long, ReviewProduct> {

    private static final ReviewProductDao INSTANCE = new ReviewProductDao();

    public static ReviewProductDao getInstance() {
        return INSTANCE;
    }
}
