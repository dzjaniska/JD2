package dao;

import entity.Product;
import entity.Shop;
import entity.UserInfo;
import org.hibernate.Session;
import org.junit.Test;
import util.EntityUtil;

public class ProductTest extends BaseDaoTest {

    @Test
    public void checkSave() {
        try (Session session = FACTORY.openSession()) {
            Product product = EntityUtil.createProduct();
            save(product);
        }
    }

    @Test
    public void checkFind() {
        try (Session session = FACTORY.openSession()) {
            Product product = EntityUtil.createProduct();
            find(product);
        }
    }
}
