package service;

import config.TestConfiguration;
import entity.Customer;
import entity.Role;
import entity.User;
import entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void save() {
        UserInfo userInfo = new UserInfo("a", "a", "a");
        UserInfo save = userInfoService.save(userInfo);
        User save1 = userService.save(new Customer("a", "a", Role.CUSTOMER, save, "a"));
        assertNotNull(save1);
    }

    @Test
    public void findFirstByLogin() {
        User admin1 = userService.findFirstByLogin("admin1");
        assertNotNull(admin1);
    }
}