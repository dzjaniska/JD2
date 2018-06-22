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
    public List<Product> findDistinctAllByCategoryAndOptionsCustom(Category category, Long[] ids, String sort, Pageable pageable) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        if (ids == null) {
            ids = new Long[0];
        }
        String queryString = buildQuery(ids);
        Query query = entityManager.createQuery(queryString, Product.class);
        query.setParameter(1, category);

        int j = 2;
        for (Long id : ids) {
            query.setParameter(j++, id);
        }

        List<Product> resultList = query.getResultList();
//        final long currentTotal = pageable.getOffset() + pageable.getPageSize();
//        Page<Product> products = new PageImpl<>(resultList, pageable, currentTotal);
        transaction.commit();

        return resultList;
    }

    private String buildQuery(Long[] ids) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select distinct p from Product p");
        if (ids.length > 0) {
            stringBuilder.append(" inner join p.options o inner join p.shopProduct sp where p.category = (?1) and o.id= (?2)");
            for (int i = 3; i < ids.length + 2; i++) {
                stringBuilder.append(" and p.id= some (select distinct p from Product p inner join p.options o where p.category = (?1) and o.id= (?" + (i) + ")");
            }
            for (int i = 0; i < ids.length - 1; i++) {
                stringBuilder.append(")");
            }
        } else {
            stringBuilder.append(" inner join p.shopProduct sp where p.category = (?1)");
        }
        stringBuilder.append(" group by p.id having sum(sp.quantity)>0");

        return stringBuilder.toString();
    }
}

