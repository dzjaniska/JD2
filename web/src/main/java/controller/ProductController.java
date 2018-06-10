package controller;

import entity.Category;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import service.OptionService;
import service.ProductService;

import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;
    private OptionService optionService;

    @Autowired
    public ProductController(ProductService productService, OptionService optionService) {
        this.productService = productService;
        this.optionService = optionService;
    }

    @ModelAttribute("allProducts")
    public List<Product> allProducts() {
        return productService.findAll();
    }

    @ModelAttribute("category")
    public Category[] categories() {
        return Category.values();
    }

    @GetMapping("/catalog")
    public String showLoginPage(Model model,
                                @RequestParam(value = "sort", required = false) String sort,
                                @RequestParam(value = "category", required = false) String category) {
//        List<Product> products = productService.findDistinctAllByCategory(Category.valueOf(category));
        List<Product> products = productService.findAllCategoryOrderByMinPrice(Category.valueOf(category));
        model.addAttribute("products", products);
        return "catalog";
    }

    @GetMapping("/createProduct")
    public String createProductPage(Model model, Category category) {
        model.addAttribute("product", new Product());
        model.addAttribute("availableOptions", optionService.findAllByCategory(category));
        return "createProduct";
    }
}
