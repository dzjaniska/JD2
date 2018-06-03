package entity;

import org.hibernate.Session;
import org.junit.Test;
import util.EntityUtil;

public class UserInfoTest extends BaseEntityTest {

    @Test
    public void checkSave() {
        try (Session session = FACTORY.openSession()) {
            save(EntityUtil.createUserInfo());
        }
    }

    @Test
    public void checkFind() {
        try (Session session = FACTORY.openSession()) {
            UserInfo savedEntity = save(EntityUtil.createUserInfo());

            find(savedEntity);
        }
    }
}
