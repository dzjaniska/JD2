package controller;

import dto.ProductDto;
import entity.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import service.ProductService;
import service.ShopService;

@Controller
public class ProductShopController {

    private ProductService productService;
    private ShopService shopService;

    @Autowired
    public ProductShopController(ProductService productService, ShopService shopService) {
        this.productService = productService;
        this.shopService = shopService;
    }

    @ModelAttribute("option")
    public Option option() {
        return new Option();
    }

    @GetMapping("/productShop")
    public String showProductPage(Model model, @RequestParam(value = "id") Long id) {
        ProductDto product = productService.findByIdWithShopsDto(id);
        model.addAttribute("product", product);

        return "productShop";
    }
}
