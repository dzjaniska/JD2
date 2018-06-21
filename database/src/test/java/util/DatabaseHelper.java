package util;

import entity.Admin;
import entity.Category;
import entity.Customer;
import entity.Option;
import entity.Orders;
import entity.Parameter;
import entity.Product;
import entity.ProductOrder;
import entity.ReviewProduct;
import entity.ReviewShop;
import entity.Role;
import entity.Shop;
import entity.ShopProduct;
import entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class DatabaseHelper {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public DatabaseHelper(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void cleanDatabase() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Option").executeUpdate();
        entityManager.createQuery("delete from Orders").executeUpdate();
        entityManager.createQuery("delete from Admin").executeUpdate();
        entityManager.createQuery("delete from ShopProduct").executeUpdate();
        entityManager.createQuery("delete from Shop").executeUpdate();
        entityManager.createQuery("delete from ReviewProduct").executeUpdate();
        entityManager.createQuery("delete from ReviewShop").executeUpdate();
        entityManager.createQuery("delete from Customer").executeUpdate();
        entityManager.createQuery("delete from Product").executeUpdate();
        entityManager.createQuery("delete from ProductOrder").executeUpdate();
        entityManager.createQuery("delete from UserInfo").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void prepareData() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Shop vek = new Shop("21vek", "description 21vek", "logo", 123L);
        Shop tnt = new Shop("TNT", "description TNT", "logo", 1234L);
        Shop element = new Shop("5element", "description 5element", "logo", 12L);
        entityManager.persist(vek);
        entityManager.persist(tnt);
        entityManager.persist(element);

        UserInfo firstUserInfo = new UserInfo("Ivan", "Ivanov", "Ivanovich");
        UserInfo secondUserInfo = new UserInfo("Petr", "Peterov", "Petrovich");
        UserInfo thirdUserInfo = new UserInfo("Sidor", "Sidoriv", "Sidorovich");
        UserInfo forthUserInfo = new UserInfo("Andrew", "Adnreev", "Andreevich");
        UserInfo fifthUserInfo = new UserInfo("Aleksey", "Alekseev", "Avekseevich");
        entityManager.persist(firstUserInfo);
        entityManager.persist(secondUserInfo);
        entityManager.persist(thirdUserInfo);
        entityManager.persist(forthUserInfo);
        entityManager.persist(fifthUserInfo);

        entityManager.persist(new Admin("admin1", "pass1", Role.ADMIN, firstUserInfo, vek));
        entityManager.persist(new Admin("admin2", "pass2", Role.ADMIN, secondUserInfo, tnt));
        entityManager.persist(new Admin("admin3", "pass3", Role.ADMIN, thirdUserInfo, element));

        Customer customer1 = new Customer("customer1", "pass4", Role.CUSTOMER, forthUserInfo, "address1");
        Customer customer2 = new Customer("customer2", "pass5", Role.CUSTOMER, fifthUserInfo, "address2");
        entityManager.persist(customer1);
        entityManager.persist(customer2);

        Option option1 = new Option(Parameter.YEAR, "2018");
        Option option2 = new Option(Parameter.YEAR, "2017");
        Option option3 = new Option(Parameter.CAPACITY, "16GB");
        Option option4 = new Option(Parameter.CAPACITY, "8GB");
        Option option5 = new Option(Parameter.CASH, "1");
        Option option6 = new Option(Parameter.CASH, "2");
        Option option7 = new Option(Parameter.CASH_L2, "2");
        Option option8 = new Option(Parameter.CASH_L2, "3");
        Option option9 = new Option(Parameter.CASH_L3, "3");
        Option option10 = new Option(Parameter.CASH_L3, "4");
        Option option11 = new Option(Parameter.PRODUCER, "AMD");
        Option option12 = new Option(Parameter.PRODUCER, "INTEL");
        Option option13 = new Option(Parameter.PRODUCER, "GEFORCE");
        Option option14 = new Option(Parameter.CORE, "i5");
        Option option15 = new Option(Parameter.CORE, "i7");
        entityManager.persist(option1);
        entityManager.persist(option2);
        entityManager.persist(option3);
        entityManager.persist(option4);
        entityManager.persist(option5);
        entityManager.persist(option6);
        entityManager.persist(option7);
        entityManager.persist(option8);
        entityManager.persist(option9);
        entityManager.persist(option10);
        entityManager.persist(option11);
        entityManager.persist(option12);
        entityManager.persist(option13);
        entityManager.persist(option14);
        entityManager.persist(option15);

        Product product1 = new Product(Category.CPU, "cpuModelName1", Arrays.asList(option1, option14, option5), "imageUrl");
        Product product2 = new Product(Category.CPU, "cpuModelName2", Arrays.asList(option2, option15, option6), "imageUrl");
        Product product3 = new Product(Category.CPU, "cpuModelName3", Arrays.asList(option2, option13, option5), "imageUrl");
        Product product4 = new Product(Category.RAM, "ramModelName1", Arrays.asList(option1, option3, option5, option7, option9), "imageUrl");
        Product product5 = new Product(Category.RAM, "ramModelName2", Arrays.asList(option1, option4, option5, option8, option9), "imageUrl");
        Product product6 = new Product(Category.RAM, "ramModelName3", Arrays.asList(option1, option3, option6, option7, option1), "imageUrl");
        Product product7 = new Product(Category.HDD, "hddModelName1", Arrays.asList(option1, option3, option7, option1), "imageUrl");
        Product product8 = new Product(Category.HDD, "hddModelName2", Arrays.asList(option2, option4, option7, option1), "imageUrl");
        Product product9 = new Product(Category.HDD, "hddModelName3", Arrays.asList(option2, option4, option6, option1), "imageUrl");
        Product product10 = new Product(Category.HDD, "testProduct", Arrays.asList(option2, option4, option6, option13), "imageUrl");
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(product3);
        entityManager.persist(product4);
        entityManager.persist(product5);
        entityManager.persist(product6);
        entityManager.persist(product7);
        entityManager.persist(product8);
        entityManager.persist(product9);
        entityManager.persist(product10);

        entityManager.persist(new ShopProduct(vek, product1, 5, 500, 0L));
        entityManager.persist(new ShopProduct(vek, product2, 4, 250, 0L));
        entityManager.persist(new ShopProduct(vek, product4, 3, 150, 0L));
        entityManager.persist(new ShopProduct(vek, product6, 2, 270, 0L));
        entityManager.persist(new ShopProduct(vek, product7, 1, 330, 0L));
        entityManager.persist(new ShopProduct(vek, product9, 3, 450, 0L));


        entityManager.persist(new ShopProduct(tnt, product1, 3, 450, 0L));
        entityManager.persist(new ShopProduct(tnt, product3, 7, 430, 0L));
        entityManager.persist(new ShopProduct(tnt, product4, 8, 170, 0L));
        entityManager.persist(new ShopProduct(tnt, product5, 4, 120, 0L));
        entityManager.persist(new ShopProduct(tnt, product7, 1, 300, 0L));
        entityManager.persist(new ShopProduct(tnt, product8, 3, 410, 0L));


        entityManager.persist(new ShopProduct(element, product2, 4, 220, 0L));
        entityManager.persist(new ShopProduct(element, product3, 3, 400, 0L));
        entityManager.persist(new ShopProduct(element, product5, 7, 110, 0L));
        entityManager.persist(new ShopProduct(element, product6, 9, 250, 0L));
        entityManager.persist(new ShopProduct(element, product8, 6, 400, 0L));
        entityManager.persist(new ShopProduct(element, product9, 1, 430, 0L));

        entityManager.persist(new ReviewShop("shopReviewText", 5, LocalDate.now(), customer1, new HashSet<Shop>(Arrays.asList(vek))));
        entityManager.persist(new ReviewShop("shopReviewText", 4, LocalDate.now(), customer2, new HashSet<Shop>(Arrays.asList(vek))));
        entityManager.persist(new ReviewShop("shopReviewText", 3, LocalDate.now(), customer1, new HashSet<Shop>(Arrays.asList(tnt))));
        entityManager.persist(new ReviewShop("shopReviewText", 2, LocalDate.now(), customer2, new HashSet<Shop>(Arrays.asList(tnt))));
        entityManager.persist(new ReviewShop("shopReviewText", 5, LocalDate.now(), customer1, new HashSet<Shop>(Arrays.asList(element))));
        entityManager.persist(new ReviewShop("shopReviewText", 4, LocalDate.now(), customer2, new HashSet<Shop>(Arrays.asList(element))));

        entityManager.persist(new ReviewProduct("shopProductText", 5, LocalDate.now(), customer1, new HashSet<Product>(Arrays.asList(product1))));
        entityManager.persist(new ReviewProduct("shopProductText", 4, LocalDate.now(), customer2, new HashSet<Product>(Arrays.asList(product1))));
        entityManager.persist(new ReviewProduct("shopProductText", 2, LocalDate.now(), customer1, new HashSet<Product>(Arrays.asList(product2))));
        entityManager.persist(new ReviewProduct("shopProductText", 3, LocalDate.now(), customer2, new HashSet<Product>(Arrays.asList(product4))));
        entityManager.persist(new ReviewProduct("shopProductText", 5, LocalDate.now(), customer1, new HashSet<Product>(Arrays.asList(product3))));
        entityManager.persist(new ReviewProduct("shopProductText", 4, LocalDate.now(), customer2, new HashSet<Product>(Arrays.asList(product4))));
        entityManager.persist(new ReviewProduct("shopProductText", 5, LocalDate.now(), customer1, new HashSet<Product>(Arrays.asList(product2))));
        entityManager.persist(new ReviewProduct("shopProductText", 4, LocalDate.now(), customer2, new HashSet<Product>(Arrays.asList(product3))));
        entityManager.persist(new ReviewProduct("shopProductText", 5, LocalDate.now(), customer1, new HashSet<Product>(Arrays.asList(product1))));
        entityManager.persist(new ReviewProduct("shopProductText", 2, LocalDate.now(), customer2, new HashSet<Product>(Arrays.asList(product2))));
        entityManager.persist(new ReviewProduct("shopProductText", 1, LocalDate.now(), customer1, new HashSet<Product>(Arrays.asList(product4))));

        Orders order1 = new Orders(customer1, LocalDateTime.now(), LocalDate.now());
        Orders order2 = new Orders(customer1, LocalDateTime.now(), LocalDate.now());
        Orders order3 = new Orders(customer2, LocalDateTime.now(), LocalDate.now());
        Orders order4 = new Orders(customer2, LocalDateTime.now(), LocalDate.now());
        entityManager.persist(order1);
        entityManager.persist(order2);
        entityManager.persist(order3);
        entityManager.persist(order4);

        entityManager.persist(new ProductOrder(product1, order1, vek, 4));
        entityManager.persist(new ProductOrder(product4, order1, vek, 2));
        entityManager.persist(new ProductOrder(product3, order2, tnt, 1));
        entityManager.persist(new ProductOrder(product8, order2, element, 4));
        entityManager.persist(new ProductOrder(product9, order2, element, 2));
        entityManager.persist(new ProductOrder(product5, order3, tnt, 1));
        entityManager.persist(new ProductOrder(product7, order4, vek, 1));
        entityManager.persist(new ProductOrder(product8, order4, tnt, 2));
        entityManager.persist(new ProductOrder(product9, order4, element, 3));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
