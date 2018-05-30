package dao;

import dao.interfaces.ReviewProductDao;
import entity.ReviewProduct;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewProductDaoImpl extends BaseDaoImpl<Long, ReviewProduct> implements ReviewProductDao {
}
