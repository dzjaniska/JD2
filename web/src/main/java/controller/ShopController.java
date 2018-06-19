package controller;

import dto.ReviewDto;
import dto.ShopPageDto;
import entity.Customer;
import entity.ReviewShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ReviewShopService;
import service.ShopService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Controller
public class ShopController {

    private ShopService shopService;
    private UserService userService;
    private ReviewShopService reviewShopService;

    @Autowired
    public ShopController(ShopService shopService, UserService userService, ReviewShopService reviewShopService) {
        this.shopService = shopService;
        this.userService = userService;
        this.reviewShopService = reviewShopService;
    }

    @ModelAttribute("reviewShop")
    public ReviewDto reviewProduct() {
        return new ReviewDto();
    }

    @GetMapping("/shop")
    public String showShopPage(Model model, @RequestParam(value = "id") Long id) {
        ShopPageDto shop = shopService.findByIdDto(id);
        model.addAttribute("shop", shop);

        return "shop";
    }

    @PostMapping("/shop")
    public String sendReview(ReviewDto reviewDto, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = (Customer) userService.findFirstByLogin(auth.getName());
        reviewShopService.save(new ReviewShop().builder()
                .user(customer)
                .shop(new HashSet<>(Arrays.asList(shopService.findById(reviewDto.getShopId()))))
                .date(LocalDate.now())
                .rating(reviewDto.getRating())
                .text(reviewDto.getText())
                .build());

        String referer = request.getHeader("Referer");

        return "redirect:" + referer;
    }
}
