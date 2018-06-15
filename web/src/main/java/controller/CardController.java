package controller;

import dto.CardDto;
import dto.CardItemDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("session")
public class CardController {

    private CardDto cardDto;

    @GetMapping("/addToCard")
    public String addToCart(HttpServletRequest request,
                            @RequestParam("productId") Long productId,
                            @RequestParam("shopId") Long shopId,
                            @RequestParam("productId") Integer quantity) {
        if (this.cardDto != null) {
            cardDto.getProductList().add(new CardItemDto(productId, shopId, quantity));
        } else {
            this.cardDto = new CardDto();
        }

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
