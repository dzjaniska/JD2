package controller;

import entity.Category;
import entity.Option;
import entity.Parameter;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import service.OptionService;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("product")
public class CatalogController {

    private ProductService productService;
    private OptionService optionService;

    @Autowired
    public CatalogController(ProductService productService, OptionService optionService) {
        this.productService = productService;
        this.optionService = optionService;
    }

    @ModelAttribute("product")
    public Product product() {
        Product product = new Product();
        product.setOptions(new ArrayList<>());

        return product;
    }

    @ModelAttribute("option")
    public Option option() {
        return new Option();
    }

    @ModelAttribute("allProducts")
    public List<Product> allProducts() {
        return productService.findAll();
    }

    @ModelAttribute("categories")
    public Category[] categories() {
        return Category.values();
    }

    @ModelAttribute("parameters")
    public Parameter[] parameters() {
        return Parameter.values();
    }

    @GetMapping("/catalog")
    public String showLoginPage(Model model,
                                @RequestParam(value = "sort", required = false) String sort,
                                @RequestParam(value = "category", required = false) String category) {
        List<Product> products = null;
        if (category != null) {
            products = productService.findDistinctAllByCategory(Category.valueOf(category));
        } else {
            products = productService.findAll();
        }
        model.addAttribute("products", products);
        return "catalog";
    }
}
