package dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    public String getUserName() {
        return "Ivan";
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
