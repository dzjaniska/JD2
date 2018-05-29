package dao.interfaces;

import entity.BaseEntity;
import entity.Category;
import entity.Option;
import entity.Product;

import java.io.Serializable;
import java.util.List;

public interface ProductDaoInterface<PK extends Serializable, T extends BaseEntity<PK>> {

    List<Product> findParametrizedProduct(Category category, Option... options);

    List<Product> findCategoryProductPagination(Category category, Integer start, Integer finish);
}
