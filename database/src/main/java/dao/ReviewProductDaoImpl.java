package dao;

import entity.ReviewProduct;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewProductDaoImpl extends BaseBaseDao<Long, ReviewProduct> {

    private static final ReviewProductDaoImpl INSTANCE = new ReviewProductDaoImpl();

    public static ReviewProductDaoImpl getInstance() {
        return INSTANCE;
    }
}
