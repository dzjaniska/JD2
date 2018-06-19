package controller;

import entity.Admin;
import entity.Customer;
import entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import service.ShopService;
import service.UserInfoService;
import service.UserService;

import java.time.LocalDateTime;

@Controller
public class RegistrationController {

    private UserService userService;
    private UserInfoService userInfoService;
    private ShopService shopService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserService userService, UserInfoService userInfoService, ShopService shopService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userInfoService = userInfoService;
        this.shopService = shopService;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("admin")
    public Admin admin() {
        return new Admin();
    }

    @ModelAttribute("customer")
    public Customer customer() {
        return new Customer();
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String show1RegistrationPage(Customer customer, Admin admin) {

        String address = "customer/cabinet";
        if (customer.getAddress() != null) {
            userInfoService.save(customer.getUserInfo());
            customer.setRole(Role.CUSTOMER);
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            userService.save(customer);
        } else {
            address = "admin/admin";
            userInfoService.save(admin.getUserInfo());
            shopService.save(admin.getShop());
            admin.setRole(Role.ADMIN);
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            userService.save(admin);
        }
        return address;
    }
}
