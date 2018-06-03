package dao.interfaces;

import entity.Category;
import entity.Option;
import entity.Product;

import java.util.List;

public interface ProductDao extends BaseDao<Long, Product> {

    List<Product> findParametrizedProduct(Category category, Option... options);

    List<Product> findCategoryProductPagination(Category category, Integer start, Integer finish);
}
