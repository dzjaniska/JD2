package dao;

import entity.UserInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoDaoImpl extends BaseBaseDao<Long, UserInfo> {

    private static final UserInfoDaoImpl INSTANCE = new UserInfoDaoImpl();

    public static UserInfoDaoImpl getInstance() {
        return INSTANCE;
    }
}
