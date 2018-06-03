package dao;

import dao.interfaces.ProductDao;
import entity.Category;
import entity.Option;
import entity.Product;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDaoImpl extends BaseDaoImpl<Long, Product> implements ProductDao {

    @Override
    public List<Product> findParametrizedProduct(Category category, Option... options) {
        try (Session session = sessionFactory.openSession()) {

            int count = options.length;
            Map<Product, Integer> productHashMap = getAllProducts(category, session, options);

            return getProductList(count, productHashMap);
        }
    }

    @Override
    public List<Product> findCategoryProductPagination(Category category, Integer start, Integer finish) {
        try (Session session = sessionFactory.openSession()) {

            return session.createQuery("select p from Product p join p.options o where p.category= :category", Product.class)
                    .setParameter("category", category)
                    .setFirstResult(start)
                    .setMaxResults(finish)
                    .list();
        }
    }

    private Map<Product, Integer> getAllProducts(Category category, Session session, Option... options) {
        Map<Product, Integer> productHashMap = new HashMap<>();

        for (Option option : options) {
            List<Product> products = session.createQuery("select p from Product p join p.options o where p.category= :category and (o.name= :parameter and o.value= :val)", Product.class)
                    .setParameter("category", category)
                    .setParameter("parameter", option.getName())
                    .setParameter("val", option.getValue())
                    .list();
            for (Product product : products) {
                productHashMap.merge(product, 1, Integer::sum);
            }
        }

        return productHashMap;
    }

    private List<Product> getProductList(int count, Map<Product, Integer> productHashMap) {
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : productHashMap.entrySet()) {
            if (entry.getValue().equals(count)) {
                productList.add(entry.getKey());
            }
        }

        return productList;
    }
}
