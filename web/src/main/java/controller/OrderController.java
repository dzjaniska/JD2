package controller;

import dto.OrderDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    @PostMapping("/order")
    public String orderPage(Model model, @ModelAttribute("order") OrderDto orderDto) {
        int a = 1;
        int aa = 1;
        return "customer/order";
    }

}
