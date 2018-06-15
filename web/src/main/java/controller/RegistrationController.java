package controller;

import entity.Admin;
import entity.Customer;
import entity.Role;
import entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import service.ShopService;
import service.UserInfoService;
import service.UserService;

@Controller
public class RegistrationController {

    private UserService userService;
    private UserInfoService userInfoService;
    private ShopService shopService;

    @ModelAttribute("admin")
    public Admin admin() {
        return new Admin();
    }

    @ModelAttribute("customer")
    public Customer customer() {
        return new Customer();
    }

    @Autowired
    public RegistrationController(UserService userService, UserInfoService userInfoService, ShopService shopService) {
        this.userService = userService;
        this.userInfoService = userInfoService;
        this.shopService = shopService;
    }

    @GetMapping("/registration")
    public String showProductPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String show1ProductPage(Customer customer, Admin admin) {

        if (customer.getAddress() != null) {
            userInfoService.save(customer.getUserInfo());
            customer.setRole(Role.CUSTOMER);
            userService.save(customer);
        } else {
            userInfoService.save(admin.getUserInfo());
            shopService.save(admin.getShop());
            admin.setRole(Role.ADMIN);
            userService.save(admin);
        }
        return "registration";
    }
}
