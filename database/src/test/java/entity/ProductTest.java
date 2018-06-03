package entity;

import org.hibernate.Session;
import org.junit.Test;
import util.EntityUtil;

public class ProductTest extends BaseEntityTest {

    @Test
    public void checkSave() {
        try (Session session = FACTORY.openSession()) {
            Product product = EntityUtil.createProduct();
            save(product);
        }
    }

    @Test
    public void checkFind() {
        try (Session session = FACTORY.openSession()) {
            Product product = EntityUtil.createProduct();
            Product savedEntity = save(product);

            find(savedEntity);
        }
    }
}
