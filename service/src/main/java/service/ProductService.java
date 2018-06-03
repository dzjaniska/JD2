package service;

import dao.ProductDaoImpl;
import entity.Category;
import entity.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductService {
    private static final ProductService INSTANCE = new ProductService();

    public List<Product> getProductsPagination(Category category, Integer start, Integer finish) {
        return ProductDaoImpl.getInstance().findCategoryProductPagination(category, start, finish);
    }

    public static ProductService getInstance() {
        return INSTANCE;
    }
}
