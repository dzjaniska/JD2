package entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class BaseDaoTest {

    public static SessionFactory FACTORY;

    @Before
    public void before() {
        FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @After
    public void after() {
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

    public <T extends BaseEntity<?>> void find(T savedObject) {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            BaseEntity baseEntity = session.find(savedObject.getClass(), savedObject.getId());
            assertNotNull("Entity is not found", baseEntity.getId());

            session.getTransaction().commit();
        }
    }

    private <T extends BaseEntity<?>> T getSavedEntity(List<T> objectList) {
        return objectList.get(objectList.size() - 1);
    }
}
