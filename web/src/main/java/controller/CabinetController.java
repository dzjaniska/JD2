package controller;

import dto.CartProductDto;
import dto.OrderDto;
import dto.ProductOrderDto;
import dto.UserOrderDto;
import entity.Customer;
import entity.Orders;
import entity.ProductOrder;
import entity.ShopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
import service.UserInfoService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("orderDto")
public class CabinetController {

    @Autowired
    private Cart cart;

    private UserService userService;
    private UserInfoService userInfoService;
    private ShopProductService shopProductService;
    private OrderService orderService;
    private ProductOrderService productOrderService;
    private ProductService productService;
    private ShopService shopService;

    @Autowired
    public CabinetController(UserService userService, UserInfoService userInfoService, ShopProductService shopProductService, OrderService orderService, ProductOrderService productOrderService, ProductService productService, ShopService shopService) {
        this.userService = userService;
        this.userInfoService = userInfoService;
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
        orderDto.getProductOrders().clear();
        List<CartProductDto> products = getProductsToShow(orderDto);

        model.addAttribute("products", products);
        model.addAttribute("customer", customer);

        return "customer/cart";
    }

    @RequestMapping(value = "/customer/cart", params = {"removeRow"})
    public String addOption(Model model, OrderDto orderDto, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        orderDto.getProductOrders().remove(rowId.intValue());
        model.addAttribute("orderDto", orderDto);
        cart.getCartDto().remove(rowId.intValue());
        orderDto.getProductOrders().clear();
        List<CartProductDto> products = getProductsToShow(orderDto);

        model.addAttribute("products", products);

        return "customer/cart";
    }

    @PostMapping("/customer/cart")
    public String saveOrder(Model model, OrderDto orderDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = (Customer) userService.findFirstByLogin(auth.getName());

        List<ProductOrderDto> productOrders = orderDto.getProductOrders();
        for (ProductOrderDto productOrder : productOrders) {
            ShopProduct shopProduct = shopProductService.findByProductAndShopAndVersion(
                    productOrder.getProductId(), productOrder.getShopId(), productOrder.getVersion());
            shopProduct.setQuantity(shopProduct.getQuantity() - productOrder.getQuantity());
            shopProductService.save(shopProduct);
        }

        Orders order = orderDto.getOrder();
        order.setOrderTime(LocalDateTime.now());
        order.setUser(customer);
        Orders savedOrder = orderService.save(order);

        for (ProductOrderDto productOrder : productOrders) {
            productOrderService.save(new ProductOrder().builder()
                    .orders(savedOrder)
                    .product(productService.findById(productOrder.getProductId()))
                    .shop(shopService.findById(productOrder.getShopId()))
                    .quantity(productOrder.getQuantity())
                    .build());
        }
        cart.getCartDto().clear();

        return "customer/order";
    }

    private List<CartProductDto> getProductsToShow(OrderDto orderDto) {
        List<CartProductDto> products = new ArrayList<>();
        cart.getCartDto().forEach(
                it -> products.add(
                        shopProductService.findFirstByProductAndShop(
                                it.getProductId(), it.getShopId())));

        for (int i = 0; i < products.size(); i++) {
            orderDto.getProductOrders().add(new ProductOrderDto());
        }
        return products;
    }

    @RequestMapping("/customer/cabinet")
    public String userCart(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = (Customer) userService.findFirstByLogin(auth.getName());
        List<UserOrderDto> orders = orderService.findAllByUserId(customer.getId());
        model.addAttribute("customer", customer);
        model.addAttribute("orders", orders);

        return "customer/cabinet";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception e) {
        model.addAttribute("errorMessage", "Product information has been changed during the order. Please, check the new information");

        return "customer/cart";
    }
}
