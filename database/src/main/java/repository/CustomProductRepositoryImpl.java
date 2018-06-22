package repository;

import entity.Category;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class CustomProductRepositoryImpl implements CustomProductRepository {

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public CustomProductRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> findDistinctAllByCategoryAndOptionsCustom(Category category, List<List<Long>> ids, String sort, Pageable pageable) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String queryString = buildQuery(ids, sort);

        Query query = entityManager.createQuery(queryString, Product.class);
        query.setParameter(1, category);

        int j = 2;
        for (List<Long> id : ids) {
            query.setParameter(j++, id);
        }

        List<Product> resultList = query.getResultList();
//        final long currentTotal = pageable.getOffset() + pageable.getPageSize();
//        Page<Product> products = new PageImpl<>(resultList, pageable, currentTotal);
        transaction.commit();

        return resultList;
    }

    private String buildQuery(List<List<Long>> ids, String sort) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select distinct p from Product p");
        for (int i = 2; i < ids.size() + 2; i++) {
            if (i == ids.size() + 1) {
                stringBuilder.append(" inner join p.options o inner join p.shopProduct sp where p.category = (?1) and o.id in (?" + (i) + ")");
            } else {
                stringBuilder.append(" inner join p.options o inner join p.shopProduct sp where p.category = (?1) and o.id in (?" + (i) + ") and p.id= some " +
                        "(select distinct p from Product p");
            }
        }
        for (int i = 0; i < ids.size() - 1; i++) {
            stringBuilder.append(")");
        }
        if (ids.size() == 0) {
            stringBuilder.append(" inner join p.shopProduct sp where p.category = (?1) group by p.id having sum(sp.quantity)>0");
        } else {
            stringBuilder.append(" group by p.id having sum(sp.quantity)>0");
        }

        switch (sort) {
           /* case "minPrice":
                stringBuilder.append(" order by min(sp.price)");
                break;
            case "maxPrice":
                stringBuilder.append(" order by min(sp.price) desc");
                break;*/
            default:
                stringBuilder.append(" order by p.id desc");
                break;
        }

        return stringBuilder.toString();
    }
}

