package controller;

import dto.CatalogPageDto;
import entity.Category;
import entity.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import service.OptionService;
import service.ProductService;

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
                              @RequestParam(value = "option", required = false) String option,
                              @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                              @RequestParam(value = "quantity", required = false, defaultValue = "5") Integer size) {
        CatalogPageDto products = null;

        if (category != null) {
            products = productService.findDistinctAllByCategory(Category.valueOf(category), PageRequest.of(page, size, new Sort(Sort.Direction.ASC, sort)));
        }
        model.addAttribute("productList", products);
        return "catalog";
    }
}
