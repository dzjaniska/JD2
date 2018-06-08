package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String showAccountPage(Model model) {
        return "hello";
    }

    @PostMapping("/hello")
    public String saveAccount() {
        return "hello";
    }
}
