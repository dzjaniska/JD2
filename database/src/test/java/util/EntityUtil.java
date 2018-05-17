package util;

import entity.Admin;
import entity.CarType;
import entity.Category;
import entity.Courier;
import entity.Customer;
import entity.Orders;
import entity.Product;
import entity.Review;
import entity.Role;
import entity.Shop;
import entity.Status;
import entity.User;
import entity.UserInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntityUtil {

    public static UserInfo createUserInfo() {
        return new UserInfo("name", "surname", "secondName");
    }

    public static Shop createShop() {
        return new Shop("shopName", "shopDescription");
    }

    public static Admin createAdmin(UserInfo userInfo, Shop shop) {
        return new Admin("login", "password", Role.ADMIN, userInfo, shop);
    }

    public static Customer createCustomer(UserInfo userInfo) {
        return new Customer("login", "password", Role.CUSTOMER, userInfo, "address");
    }

    public static Courier createCourier(UserInfo userInfo) {
        return new Courier("login", "password", Role.COURIER, userInfo, CarType.CAR);
    }

    public static Orders createOrder(User user) {
        return new Orders(user, LocalDateTime.now(), LocalDateTime.now(), Status.PROCESSING);
    }

    public static Review createReview(User user) {
        return new Review("text", 5, LocalDate.now(), user);
    }

    public static Product createProduct() {
        return new Product(Category.CPU, "description", "image_url");
    }
}
