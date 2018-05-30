package dao;

import dao.interfaces.ShopDao;
import entity.Shop;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDaoImpl extends BaseDaoImpl<Long, Shop> implements ShopDao {
}
