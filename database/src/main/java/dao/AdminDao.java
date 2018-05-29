package dao;

import entity.Admin;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminDao extends BaseDao<Long, Admin> {

    private static final AdminDao INSTANCE = new AdminDao();

    public static AdminDao getInstance() {
        return INSTANCE;
    }
}
