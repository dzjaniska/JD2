package controller;

import dto.CatalogDto;
import dto.ReviewDto;
import entity.Customer;
import entity.Option;
import entity.ReviewProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ProductService;
import service.ReviewProductService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Controller
public class ProductController {

    private ProductService productService;
    private ReviewProductService reviewProductService;
    private UserService userService;

    @Autowired
    public ProductController(ProductService productService, ReviewProductService reviewProductService, UserService userService) {
        this.productService = productService;
        this.reviewProductService = reviewProductService;
        this.userService = userService;
    }

    @ModelAttribute("option")
    public Option option() {
        return new Option();
    }

    @ModelAttribute("reviewProduct")
    public ReviewDto reviewProduct() {
        return new ReviewDto();
    }

    @GetMapping("/product")
    public String showProductPage(Model model, @RequestParam(value = "id") Long id) {
        CatalogDto product = productService.findByIdCatalogItem(id);
        model.addAttribute("product", product);

        return "product";
    }

    @PostMapping("/product")
    public String sendReview(ReviewDto reviewDto, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = (Customer) userService.findFirstByLogin(auth.getName());
        reviewProductService.save(new ReviewProduct().builder()
                .user(customer)
                .product(new HashSet<>(Arrays.asList(productService.findById(reviewDto.getProductId()))))
                .date(LocalDate.now())
                .rating(reviewDto.getRating())
                .text(reviewDto.getText())
                .build());

        String referer = request.getHeader("Referer");

        return "redirect:" + referer;
    }
}
