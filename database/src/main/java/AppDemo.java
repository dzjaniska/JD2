import dao.CustomerDaoImpl;
import dao.OptionDaoImpl;
import dao.OrdersDaoImpl;
import dao.ProductDaoImpl;
import dao.ReviewProductDaoImpl;
import dao.RouteDaoImpl;
import dao.UserInfoDaoImpl;
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
        List<Product> categoryProduct = ProductDaoImpl.getInstance().findCategoryProductPagination(Category.HDD, 1, 2);
    }

    private static List<Product> test5() {
        return ProductDaoImpl.getInstance().findParametrizedProduct(Category.HDD,
                new Option(Parameter.TDP, "6VT"),
                new Option(Parameter.PRODUCER, "INTEL"),
                new Option(Parameter.CAPACITY, "250GB")
        );
    }

    private static void test4() {
        HashSet<Product> products = new HashSet<>();
        products.add(ProductDaoImpl.getInstance().find(1L));

        Customer customer = CustomerDaoImpl.getInstance().find(1L);

        Long reviewId = ReviewProductDaoImpl.getInstance().save(new ReviewProduct("text", 5, LocalDate.now(), customer, products));
    }

    private static void test3() {
        HashSet<Orders> orders = new HashSet<>();

        orders.add(OrdersDaoImpl.getInstance().find(1L));
        orders.add(OrdersDaoImpl.getInstance().find(2L));
        orders.add(OrdersDaoImpl.getInstance().find(3L));

        Long routeId = RouteDaoImpl.getInstance().save(
                new Route(
                        CustomerDaoImpl.getInstance().find(1L),
                        LocalDate.now(),
                        Status.PROCESSING,
                        orders));
    }

    private static void test2() {
        Long orderId = OrdersDaoImpl.getInstance().save(new Orders(
                CustomerDaoImpl.getInstance().find(1L),
                LocalDateTime.now(),
                LocalDateTime.now(),
                Status.PROCESSING));
    }

    private static void test1() {
        Long productId = ProductDaoImpl.getInstance().save(new Product(Category.HDD, "description", "url"));
        Product foundProduct = ProductDaoImpl.getInstance().find(productId);
        HashSet<Product> products = new HashSet<>();
        products.add(foundProduct);

        Long userInfoId = UserInfoDaoImpl.getInstance().save(new UserInfo("name", "surname", "second_name"));
        UserInfo userInfo = UserInfoDaoImpl.getInstance().find(userInfoId);

        Long userId = CustomerDaoImpl.getInstance().save(new Customer("login", "password", Role.CUSTOMER, userInfo, "address"));
        Customer customer = CustomerDaoImpl.getInstance().find(userId);

        Long reviewId = ReviewProductDaoImpl.getInstance().save(new ReviewProduct("text", 5, LocalDate.now(), customer, products));
        ReviewProduct reviewProduct = ReviewProductDaoImpl.getInstance().find(reviewId);

        System.out.println(reviewProduct);
    }

    private static void test() {
        Serializable productId = ProductDaoImpl.getInstance().save(new Product(Category.HDD, "description", "url"));
        Product foundProduct = ProductDaoImpl.getInstance().find((Long) productId);

        Option option1 = new Option(Parameter.PRODUCER, foundProduct, "INTEL");
        Option option2 = new Option(Parameter.CAPACITY, foundProduct, "250GB");
        Option option3 = new Option(Parameter.TDP, foundProduct, "6VT");

        OptionDaoImpl.getInstance().save(option1);
        OptionDaoImpl.getInstance().save(option2);
        OptionDaoImpl.getInstance().save(option3);


        Product foundProduct2 = ProductDaoImpl.getInstance().find((Long) productId);
    }
}
