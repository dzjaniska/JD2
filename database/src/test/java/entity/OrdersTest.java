package entity;

import org.hibernate.Session;
import org.junit.Test;
import util.EntityUtil;

public class OrdersTest extends BaseEntityTest {

    @Test
    public void checkSave() {
        try (Session session = FACTORY.openSession()) {
            UserInfo userInfo = EntityUtil.createUserInfo();
            Courier courier = EntityUtil.createCourier(userInfo);
            Orders orders = EntityUtil.createOrder(courier);
            save(userInfo, courier, orders);
        }
    }

    @Test
    public void checkFind() {
        try (Session session = FACTORY.openSession()) {
            UserInfo userInfo = EntityUtil.createUserInfo();
            Courier courier = EntityUtil.createCourier(userInfo);
            Orders orders = EntityUtil.createOrder(courier);
            BaseEntity<Long> savedEntity = save(userInfo, courier, orders);

            find(savedEntity);
        }
    }
}
