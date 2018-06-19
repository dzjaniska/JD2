package controller;

import dto.CatalogDto;
import entity.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import service.ProductService;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("option")
    public Option option() {
        return new Option();
    }

    @GetMapping("/product")
    public String showProductPage(Model model, @RequestParam(value = "id") Long id) {
        CatalogDto product = productService.findByIdCatalogItem(id);

        model.addAttribute("product", product);
        return "product";
    }
}
