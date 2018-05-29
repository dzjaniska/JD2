package dao;

import entity.UserInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoDao extends BaseDao<Long, UserInfo> {

    private static final UserInfoDao INSTANCE = new UserInfoDao();

    public static UserInfoDao getInstance() {
        return INSTANCE;
    }
}
