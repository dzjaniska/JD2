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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class UserInfoServiceTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void save() {
        UserInfo userInfo = new UserInfo("a", "a", "a");
        UserInfo save = userInfoService.save(userInfo);
        assertNotNull(save);
    }
}