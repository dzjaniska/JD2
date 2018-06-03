package servlet;

import entity.Category;
import entity.Product;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer page = Integer.valueOf(req.getParameter("page"));
        Integer amount = Integer.valueOf(req.getParameter("amount"));
        Category category = Category.valueOf(req.getParameter("category"));
        List<Product> productsPagination = ProductService.getInstance().getProductsPagination(category, page, amount);
        List<Integer> pages = new ArrayList<>();
        Integer pagesNumber = productsPagination.size() / amount;

        req.setAttribute("pages", productsPagination);
        req.setAttribute("products", productsPagination);
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/products.jsp")
                .forward(req, resp);
    }
}
