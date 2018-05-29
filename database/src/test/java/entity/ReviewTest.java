package entity;

import org.hibernate.Session;
import org.junit.Test;
import util.EntityUtil;

import java.util.HashSet;

public class ReviewTest extends BaseDaoTest {

    @Test
    public void checkReviewShopSave() {
        try (Session session = FACTORY.openSession()) {
            UserInfo userInfo = EntityUtil.createUserInfo();
            Customer customer = EntityUtil.createCustomer(userInfo);
            Shop shop = EntityUtil.createShop();
            HashSet<Shop> shops = new HashSet<>();
            shops.add(shop);
            ReviewShop reviewShop = EntityUtil.createReviewShop(customer, shops);
            save(userInfo, customer, shop, reviewShop);
        }
    }

    @Test
    public void checkReviewShopFind() {
        try (Session session = FACTORY.openSession()) {
            UserInfo userInfo = EntityUtil.createUserInfo();
            Customer customer = EntityUtil.createCustomer(userInfo);
            Shop shop = EntityUtil.createShop();
            HashSet<Shop> shops = new HashSet<>();
            shops.add(shop);
            ReviewShop reviewShop = EntityUtil.createReviewShop(customer, shops);
            BaseEntity<Long> savedEntity = save(userInfo, customer, shop, reviewShop);

            find(savedEntity);
        }
    }

    @Test
    public void checkReviewProductSave() {
        try (Session session = FACTORY.openSession()) {
            UserInfo userInfo = EntityUtil.createUserInfo();
            Customer customer = EntityUtil.createCustomer(userInfo);
            Product product = EntityUtil.createProduct();
            HashSet<Product> products = new HashSet<>();
            products.add(product);
            ReviewProduct review = EntityUtil.createReviewProduct(customer, products);
            save(userInfo, customer, product, review);
        }
    }

    @Test
    public void checkReviewProductFind() {
        try (Session session = FACTORY.openSession()) {
            UserInfo userInfo = EntityUtil.createUserInfo();
            Customer customer = EntityUtil.createCustomer(userInfo);
            Product product = EntityUtil.createProduct();
            HashSet<Product> products = new HashSet<>();
            products.add(product);
            ReviewProduct review = EntityUtil.createReviewProduct(customer, products);
            BaseEntity<Long> savedEntity = save(userInfo, customer, product, review);

            find(savedEntity);
        }
    }
}
