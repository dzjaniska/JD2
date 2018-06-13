package controller;

import entity.Option;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import service.OptionService;
import service.ProductService;

@Controller
public class ProductController {

    private ProductService productService;
    private OptionService optionService;

    @Autowired
    public ProductController(ProductService productService, OptionService optionService) {
        this.productService = productService;
        this.optionService = optionService;
    }

    @ModelAttribute("option")
    public Option option() {
        return new Option();
    }

    @GetMapping("/product")
    public String showProductPage(Model model, @RequestParam(value = "id") Long id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product";
    }
}
