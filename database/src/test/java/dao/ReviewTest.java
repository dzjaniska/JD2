package dao;

import entity.Customer;
import entity.Review;
import entity.UserInfo;
import org.hibernate.Session;
import org.junit.Test;
import util.EntityUtil;

public class ReviewTest extends BaseDaoTest {

    @Test
    public void checkSave() {
        try (Session session = FACTORY.openSession()) {
            UserInfo userInfo = EntityUtil.createUserInfo();
            Customer customer = EntityUtil.createCustomer(userInfo);
            Review review = EntityUtil.createReview(customer);
            save(userInfo, customer, review);
        }
    }

    @Test
    public void checkFind() {
        try (Session session = FACTORY.openSession()) {
            UserInfo userInfo = EntityUtil.createUserInfo();
            Customer customer = EntityUtil.createCustomer(userInfo);
            Review review = EntityUtil.createReview(customer);
            find(userInfo, customer, review);
        }
    }
}
