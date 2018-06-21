package repository;

import config.TestConfiguration;
import entity.Orders;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import util.DatabaseHelper;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class OrderRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void findAllByUserId() {
        User user = userRepository.findFirstByLogin("customer1");
        List<Orders> orders = orderRepository.findAllByUserId(user.getId());
        final int expectedSize = 2;
        assertEquals(expectedSize, orders.size());
    }
}