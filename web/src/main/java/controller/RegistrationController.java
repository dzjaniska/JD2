package controller;

import entity.Admin;
import entity.Customer;
import entity.Option;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import service.UserService;

@Controller
public class RegistrationController {

    private UserService userService;

    @ModelAttribute("option")
    public Option option() {
        return new Option();
    }

    @ModelAttribute("customer")
    public Customer customer() {
        return new Customer();
    }

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showProductPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String show1ProductPage() {
        return "registration";
    }
}
