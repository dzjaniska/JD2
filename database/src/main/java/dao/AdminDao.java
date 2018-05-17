package dao;

import entity.Admin;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminDao {

    private static final AdminDao INSTANCE = new AdminDao();

    public Admin getAdminById(Long id) {
        return new Admin();
    }

    public static AdminDao getInstance() {
        return INSTANCE;
    }
}
