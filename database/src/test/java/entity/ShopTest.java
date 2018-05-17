package entity;

import org.hibernate.Session;
import org.junit.Test;
import util.EntityUtil;

public class ShopTest extends BaseDaoTest {

    @Test
    public void checkSave() {
        try (Session session = FACTORY.openSession()) {
            Shop shop = EntityUtil.createShop();
            save(shop);
        }
    }

    @Test
    public void checkFind() {
        try (Session session = FACTORY.openSession()) {
            Shop shop = EntityUtil.createShop();
            Shop savedEntity = save(shop);

            find(savedEntity);
        }
    }
}