package controller;

import dto.CatalogDto;
import dto.CatalogPageDto;
import entity.Category;
import entity.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import service.OptionService;
import service.ProductService;

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

    @ModelAttribute("categories")
    public Category[] categories() {
        return Category.values();
    }

    @ModelAttribute("parameters")
    public Parameter[] parameters() {
        return Parameter.values();
    }

    @GetMapping("/catalog")
    public String showCatalog(Model model,
                              @RequestParam(value = "sort", required = false, defaultValue = "description") String sort,
                              @RequestParam(value = "category", required = false) String category,
                              @RequestParam(value = "option", required = false) Long[] optionId,
                              @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                              @RequestParam(value = "quantity", required = false, defaultValue = "5") Integer size) {
        CatalogPageDto products = null;

        if (category != null) {
            products = productService.findDistinctAllByCategoryAndOptions(Category.valueOf(category), optionId, sort, PageRequest.of(page, size));
        } else {
            model.addAttribute("greetings", "Welcome to our shop! Please, choose category");
        }

        model.addAttribute("productList", products);
        return "catalog";
    }


    @GetMapping("/search")
    public String searchProduct(Model model,
                                @RequestParam(value = "name", required = true) String name) {
        List<CatalogDto> products = productService.findAllByDescriptionContainingIgnoreCaseCatalog(name);
        model.addAttribute("productList", products);

        return "search";
    }
}
