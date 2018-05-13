package dao;

import entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class BaseDaoTest {

    protected static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void after() {
        FACTORY.close();
    }

    public <T extends BaseEntity<?>> T save(T... object) {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();

            List<T> objectList = new ArrayList<>();

            Arrays.asList(object).forEach(it -> {
                session.save(it);
                objectList.add(it);
                assertNotNull("Entity is not saved", it.getId());
            });

            session.getTransaction().commit();

            return getSavedEntity(objectList);
        }
    }

    public <T extends BaseEntity<?>> void find(T... object) {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            T savedEntity = save(object);
            BaseEntity baseEntity = session.find(savedEntity.getClass(), savedEntity.getId());
            assertNotNull("Entity is not found", baseEntity.getId());

            session.getTransaction().commit();
        }
    }

    private <T extends BaseEntity<?>> T getSavedEntity(List<T> objectList) {
        return objectList.get(objectList.size() - 1);
    }
}
