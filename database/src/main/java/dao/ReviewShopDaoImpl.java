package dao;

import dao.interfaces.ReviewShopDao;
import entity.ReviewShop;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewShopDaoImpl extends BaseDaoImpl<Long, ReviewShop> implements ReviewShopDao {
}
