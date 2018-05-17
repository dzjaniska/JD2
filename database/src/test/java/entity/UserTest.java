package entity;

import org.hibernate.Session;
import org.junit.Test;
import util.EntityUtil;

public class UserTest extends BaseDaoTest {

    @Test
    public void checkAdminSave() {
        try (Session session = FACTORY.openSession()) {
            UserInfo userInfo = EntityUtil.createUserInfo();
            Shop shop = EntityUtil.createShop();
            Admin admin = EntityUtil.createAdmin(userInfo, shop);
            save(userInfo, shop, admin);
        }
    }

    @Test
    public void checkAdminFind() {
        try (Session session = FACTORY.openSession()) {
            UserInfo userInfo = EntityUtil.createUserInfo();
            Shop shop = EntityUtil.createShop();
            Admin admin = EntityUtil.createAdmin(userInfo, shop);
            BaseEntity<Long> savedEntity = save(userInfo, shop, admin);

            find(savedEntity);
        }
    }

    @Test
    public void checkCustomerSave() {
        try (Session session = FACTORY.openSession()) {
            UserInfo userInfo = EntityUtil.createUserInfo();
            Customer customer = EntityUtil.createCustomer(userInfo);
            save(userInfo, customer);
        }
    }

    @Test
    public void checkCustomerFind() {
        try (Session session = FACTORY.openSession()) {
            UserInfo userInfo = EntityUtil.createUserInfo();
            Customer customer = EntityUtil.createCustomer(userInfo);
            BaseEntity<Long> savedEntity = save(userInfo, customer);

            find(savedEntity);
        }
    }

    @Test
    public void checkCourierSave() {
        try (Session session = FACTORY.openSession()) {
            UserInfo userInfo = EntityUtil.createUserInfo();
            Courier courier = EntityUtil.createCourier(userInfo);
            save(userInfo, courier);
        }
    }

    @Test
    public void checkCourierFind() {
        try (Session session = FACTORY.openSession()) {
            UserInfo userInfo = EntityUtil.createUserInfo();
            Courier courier = EntityUtil.createCourier(userInfo);
            BaseEntity<Long> savedEntity = save(userInfo, courier);

            find(savedEntity);
        }
    }
}
