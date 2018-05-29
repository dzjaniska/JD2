import dao.CustomerDao;
import dao.OptionDao;
import dao.OrdersDao;
import dao.ProductDao;
import dao.ReviewProductDao;
import dao.RouteDao;
import dao.UserInfoDao;
import entity.Category;
import entity.Customer;
import entity.Option;
import entity.Orders;
import entity.Parameter;
import entity.Product;
import entity.ReviewProduct;
import entity.Role;
import entity.Route;
import entity.Status;
import entity.UserInfo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

public class AppDemo {

    public static void main(String[] args) {
        List<Product> productList = test5();
        System.out.println();
    }

    private static void test6() {
        List<Product> categoryProduct = ProductDao.getInstance().findCategoryProductPagination(Category.HDD, 1, 2);
    }

    private static List<Product> test5() {
        return ProductDao.getInstance().findParametrizedProduct(Category.HDD,
                new Option(Parameter.TDP, "6VT"),
                new Option(Parameter.PRODUCER, "INTEL"),
                new Option(Parameter.CAPACITY, "250GB")
        );
    }

    private static void test4() {
        HashSet<Product> products = new HashSet<>();
        products.add(ProductDao.getInstance().find(1L));

        Customer customer = CustomerDao.getInstance().find(1L);

        Long reviewId = ReviewProductDao.getInstance().save(new ReviewProduct("text", 5, LocalDate.now(), customer, products));
    }

    private static void test3() {
        HashSet<Orders> orders = new HashSet<>();

        orders.add(OrdersDao.getInstance().find(1L));
        orders.add(OrdersDao.getInstance().find(2L));
        orders.add(OrdersDao.getInstance().find(3L));

        Long routeId = RouteDao.getInstance().save(
                new Route(
                        CustomerDao.getInstance().find(1L),
                        LocalDate.now(),
                        Status.PROCESSING,
                        orders));
    }

    private static void test2() {
        Long orderId = OrdersDao.getInstance().save(new Orders(
                CustomerDao.getInstance().find(1L),
                LocalDateTime.now(),
                LocalDateTime.now(),
                Status.PROCESSING));
    }

    private static void test1() {
        Long productId = ProductDao.getInstance().save(new Product(Category.HDD, "description", "url"));
        Product foundProduct = ProductDao.getInstance().find(productId);
        HashSet<Product> products = new HashSet<>();
        products.add(foundProduct);

        Long userInfoId = UserInfoDao.getInstance().save(new UserInfo("name", "surname", "second_name"));
        UserInfo userInfo = UserInfoDao.getInstance().find(userInfoId);

        Long userId = CustomerDao.getInstance().save(new Customer("login", "password", Role.CUSTOMER, userInfo, "address"));
        Customer customer = CustomerDao.getInstance().find(userId);

        Long reviewId = ReviewProductDao.getInstance().save(new ReviewProduct("text", 5, LocalDate.now(), customer, products));
        ReviewProduct reviewProduct = ReviewProductDao.getInstance().find(reviewId);

        System.out.println(reviewProduct);
    }

    private static void test() {
        Serializable productId = ProductDao.getInstance().save(new Product(Category.HDD, "description", "url"));
        Product foundProduct = ProductDao.getInstance().find((Long) productId);

        Option option1 = new Option(Parameter.PRODUCER, foundProduct, "INTEL");
        Option option2 = new Option(Parameter.CAPACITY, foundProduct, "250GB");
        Option option3 = new Option(Parameter.TDP, foundProduct, "6VT");

        OptionDao.getInstance().save(option1);
        OptionDao.getInstance().save(option2);
        OptionDao.getInstance().save(option3);


        Product foundProduct2 = ProductDao.getInstance().find((Long) productId);
    }
}
