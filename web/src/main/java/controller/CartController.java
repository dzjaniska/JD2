package controller;

import dto.CartProductDto;
import dto.OrderDto;
import dto.ProductOrderDto;
import entity.Customer;
import entity.Orders;
import entity.ProductOrder;
import entity.ShopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pojo.Cart;
import service.OrderService;
import service.ProductOrderService;
import service.ProductService;
import service.ShopProductService;
import service.ShopService;
import service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("orderDto")
public class CartController {

    @Autowired
    private Cart cart;

    private UserService userService;
    private ShopProductService shopProductService;
    private OrderService orderService;
    private ProductOrderService productOrderService;
    private ProductService productService;
    private ShopService shopService;

    @Autowired
    public CartController(UserService userService, ShopProductService shopProductService, OrderService orderService, ProductOrderService productOrderService, ProductService productService, ShopService shopService) {
        this.userService = userService;
        this.shopProductService = shopProductService;
        this.orderService = orderService;
        this.productOrderService = productOrderService;
        this.productService = productService;
        this.shopService = shopService;
    }

    @ModelAttribute("orderDto")
    public OrderDto orderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrder(new Orders());
        orderDto.setProductOrders(new ArrayList<>());
        return orderDto;
    }

    @RequestMapping("/customer/cart")
    public String userCart(Model model, OrderDto orderDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = (Customer) userService.findFirstByLogin(auth.getName());
        List<CartProductDto> products = new ArrayList<>();
        cart.getCartDto().forEach(
                it -> products.add(
                        shopProductService.findFirstByProductAndShop(
                                it.getProductId(), it.getShopId())));

        for (int i = 0; i < products.size(); i++) {
            orderDto.getProductOrders().add(new ProductOrderDto());
        }

        model.addAttribute("products", products);
        model.addAttribute("customer", customer);

        return "customer/cart";
    }

    @PostMapping("/customer/order")
    public String saveOrder(OrderDto orderDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = (Customer) userService.findFirstByLogin(auth.getName());

        Orders order = orderDto.getOrder();
        order.setOrderTime(LocalDateTime.now());
        order.setUser(customer);
        Orders savedOrder = orderService.save(order);


        List<ProductOrderDto> productOrders = orderDto.getProductOrders();
        for (ProductOrderDto productOrder : productOrders) {
            productOrderService.save(new ProductOrder().builder()
                    .orders(savedOrder)
                    .product(productService.findById(productOrder.getProductId()))
                    .shop(shopService.findById(productOrder.getShopId()))
                    .quantity(productOrder.getQuantity())
                    .build());
            ShopProduct shopProduct = shopProductService.findByProductAndShop(
                    productOrder.getProductId(), productOrder.getShopId());
            shopProduct.setQuantity(shopProduct.getQuantity() - productOrder.getQuantity());
            shopProductService.save(shopProduct);
        }
        cart.getCartDto().clear();

        return "customer/order";
    }
}
