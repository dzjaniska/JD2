package repository;

import entity.Category;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

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
    public List<Product> findDistinctAllByCategoryAndOptionsCustom(Category category, Long[] id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("select distinct p from Product p inner join p.options o inner join p.shopProduct sp where p.category = (?1)");
        stringBuilder.append("select distinct p from Product p inner join p.options o inner join p.shopProduct sp where p.category = (?1) and o.id= (?2) and o.id= (?3)");
        /*int i = 2;

        for (Long aLong : id) {
            stringBuilder.append(" and o.id= (?" + i++ + ")");
        }
        String queryString = stringBuilder.append(" group by p.id having sum(sp.quantity)>0").toString();
*/

        String queryString = stringBuilder.toString();
        Query query = entityManager.createQuery(queryString, Product.class);
        query.setParameter(1, category);
        query.setParameter(2, 1L);
        query.setParameter(3, 2L);
        int j = 2;
/*
        for (Long aLong : id) {
            query.setParameter(j++, aLong);
        }*/
        List<Product> resultList = query.getResultList();
        transaction.commit();

        return resultList;
    }
}

