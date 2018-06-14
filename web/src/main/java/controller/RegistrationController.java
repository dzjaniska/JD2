package controller;

import entity.Admin;
import entity.Customer;
import entity.Role;
import entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import service.UserInfoService;
import service.UserService;

@Controller
public class RegistrationController {

    private UserService userService;
    private UserInfoService userInfoService;

    @ModelAttribute("admin")
    public Admin admin() {
        return new Admin();
    }

    @ModelAttribute("customer")
    public Customer customer() {
        return new Customer();
    }

    @ModelAttribute("info")
    public UserInfo userInfo() {
        return new UserInfo();
    }

    @Autowired
    public RegistrationController(UserService userService, UserInfoService userInfoService) {
        this.userService = userService;
        this.userInfoService = userInfoService;
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
            admin.setRole(Role.ADMIN);
            userService.save(admin);
        }
        return "registration";
    }
}
