package controller;

import dto.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.Cart;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AddToCartController {

    @Autowired
    private Cart cart;

    @GetMapping("/addToCart")
    public String addToCart(HttpServletRequest request,
                            @RequestParam("productId") Long productId,
                            @RequestParam("shopId") Long shopId) {
        CartItemDto cartItemDto = new CartItemDto(productId, shopId);
        if (!cart.getCartDto().contains(cartItemDto)) {
            cart.getCartDto().add(cartItemDto);
        }
        String referer = request.getHeader("Referer");

        return "redirect:" + referer;
    }


}
