package controller;

import dto.AdminOrderDto;
import dto.ShopProductDto;
import entity.Admin;
import entity.ShopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import service.ProductOrderService;
import service.ProductService;
import service.ShopProductService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes("admin")
public class AdminController {

    private UserService userService;
    private ShopProductService shopProductService;
    private ProductOrderService productOrderService;
    private ProductService productService;

    @ModelAttribute("shopProduct")
    public ShopProductDto shopProduct() {
        return new ShopProductDto();
    }

    @Autowired
    public AdminController(UserService userService, ShopProductService shopProductService, ProductOrderService productOrderService, ProductService productService) {
        this.userService = userService;
        this.shopProductService = shopProductService;
        this.productOrderService = productOrderService;
        this.productService = productService;
    }

    @GetMapping("/admin/admin")
    public String showAdminPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = (Admin) userService.findFirstByLogin(auth.getName());

        model.addAttribute("admin", admin);
        return "admin/admin";
    }

    @GetMapping("/admin/shopOrders")
    public String showShopOrders(Model model, Admin admin) {
        List<AdminOrderDto> orders = productOrderService.findAllByShopIdOrderByOrdersDesc(admin.getShop().getId());

        model.addAttribute("orders", orders);
        model.addAttribute("admin", admin);
        return "admin/shopOrders";
    }

    @GetMapping("/admin/shopProducts")
    public String showShopProducts(Model model, Admin admin) {
        List<ShopProductDto> products = shopProductService.findAllByShopIdAndQuantityGreaterThan(admin.getShop().getId());

        model.addAttribute("products", products);
        model.addAttribute("admin", admin);
        return "admin/shopProducts";
    }

    @PostMapping("/admin/shopProducts")
    public String changeStock(ShopProductDto shopProductDto, HttpServletRequest request, Admin admin) {
        ShopProduct shopProduct = shopProductService.findByProductAndShop(shopProductDto.getProductId(), admin.getShop().getId());

        if (shopProductDto.getPrice() != null) {
            shopProduct.setPrice(shopProductDto.getPrice());
        }
        if (shopProductDto.getQuantity() != null) {
            shopProduct.setQuantity(shopProductDto.getQuantity());
        }

        shopProductService.save(shopProduct);

        String referer = request.getHeader("Referer");

        return "redirect:" + referer;
    }


    @GetMapping("/admin/search")
    public String changeStock(Model model, Admin admin,
                              @RequestParam(value = "name", required = true) String name) {

        List<ShopProductDto> products = productService.findAllByDescriptionContainingIgnoreCase(name);

        model.addAttribute("searchResult", products);
        model.addAttribute("admin", admin);

        return "admin/search";
    }

    @PostMapping("/admin/search")
    public String addToStock(ShopProductDto shopProductDto, Admin admin) {
        ShopProduct shopProduct = shopProductService.findByProductAndShop(shopProductDto.getProductId(), admin.getShop().getId());
        if (shopProduct == null) {
            shopProductService.save(new ShopProduct().builder()
                    .product(productService.findById(shopProductDto.getProductId()))
                    .shop(admin.getShop())
                    .price(shopProductDto.getPrice())
                    .quantity(shopProductDto.getQuantity())
                    .build());
        } else {
            shopProduct.setPrice(shopProductDto.getPrice());
            shopProduct.setQuantity(shopProductDto.getQuantity());
            shopProductService.save(shopProduct);
        }

        return "redirect:/admin/shopProducts";
    }
}
