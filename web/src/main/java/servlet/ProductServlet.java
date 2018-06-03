package servlet;

import entity.Category;
import entity.Option;
import entity.Parameter;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
@Component
public class ProductServlet extends HttpServlet {

    private ProductService productService;

    @Autowired
    public ProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer page = Integer.valueOf(req.getParameter("page"));
        Integer size = Integer.valueOf(req.getParameter("size"));
        Integer startPrice = Integer.valueOf(req.getParameter("startPrice"));
        Integer endPrice = Integer.valueOf(req.getParameter("endPrice"));
        String optionValue = req.getParameter("optionValue");
        Option option = new Option(Parameter.YEAR, optionValue);
        Category category = Category.valueOf(req.getParameter("category"));
        List<Product> productList = productService.productPag(category, option, startPrice, endPrice, page, size);

        req.setAttribute("products", productList);
        req.getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/products.jsp")
                .forward(req, resp);
    }
}
