package dao;

import entity.Courier;
import entity.Orders;
import entity.UserInfo;
import org.hibernate.Session;
import org.junit.Test;
import util.EntityUtil;

public class OrdersTest extends BaseDaoTest {

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
            find(userInfo, courier, orders);
        }
    }
}
