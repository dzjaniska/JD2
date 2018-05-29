package dao;

import entity.Admin;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminDaoImpl extends BaseBaseDao<Long, Admin> {

    private static final AdminDaoImpl INSTANCE = new AdminDaoImpl();

    public static AdminDaoImpl getInstance() {
        return INSTANCE;
    }
}
