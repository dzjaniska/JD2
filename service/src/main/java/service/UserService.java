package service;

import dao.UserDao;
import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    public User getUser() {
        return new User(UserDao.getInstance().getUserName());
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
