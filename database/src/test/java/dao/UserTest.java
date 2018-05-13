package dao;

import entity.Admin;
import entity.Courier;
import entity.Customer;
import entity.Shop;
import entity.UserInfo;
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
            find(userInfo, shop, admin);
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
            find(userInfo, customer);
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
            find(userInfo, courier);
        }
    }
}
