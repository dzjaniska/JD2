package dao;

import entity.UserInfo;
import org.hibernate.Session;
import org.junit.Test;
import util.EntityUtil;

public class UserInfoTest extends BaseDaoTest {

    @Test
    public void checkSave() {
        try (Session session = FACTORY.openSession()) {
            save(EntityUtil.createUserInfo());
        }
    }

    @Test
    public void checkFind() {
        try (Session session = FACTORY.openSession()) {
            UserInfo userInfo = EntityUtil.createUserInfo();
            save(userInfo);
            find(userInfo);
        }
    }
}
