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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import service.OptionService;
import service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("product")
public class ProductController {

    private ProductService productService;
    private OptionService optionService;

    @Autowired
    public ProductController(ProductService productService, OptionService optionService) {
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
            products = productService.findAllCategoryOrderByMinPrice(Category.valueOf(category));
        } else {
            products = productService.findAll();
//            products = productService.findDistinctAllByCategory(Category.valueOf(category));
        }
        model.addAttribute("products", products);
        return "catalog";
    }

    @RequestMapping("/createProduct")
    public String createPage(Product product) {
        return "createProduct";
    }

    @RequestMapping(value = "/createProduct", params = {"addRow"})
    public String createProductPage(Model model, Product product) {
        product.getOptions().add(new Option());
        model.addAttribute("product", product);
        return "createProduct";
    }

    @RequestMapping(value = "/createProduct", params = {"removeRow"})
    public String addOption(Model model, Product product, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        product.getOptions().remove(rowId.intValue());
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/createProduct")
    public String createProduct(Product product) {
        product.getOptions().stream().map(optionService::save).collect(Collectors.toList());
        productService.save(product);
        System.out.println();
        return "createProduct";
    }

}
