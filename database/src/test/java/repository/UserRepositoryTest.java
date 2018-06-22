package repository;

import config.TestConfiguration;
import entity.Admin;
import entity.Customer;
import entity.Role;
import entity.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import util.DatabaseHelper;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class UserRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void test(){
        UserInfo userInfo = new UserInfo("asd", "sad", "asd");
        Admin admin = new Admin("as", "as", Role.CUSTOMER, userInfo, null);
        Customer aadmin = new Customer("as", "as", Role.CUSTOMER, userInfo, null);
    }
}