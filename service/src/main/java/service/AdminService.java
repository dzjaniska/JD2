package service;

import dao.AdminDao;
import entity.Admin;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminService {

    private static final AdminService INSTANCE = new AdminService();

    public Admin getAdminById(Long id) {
        return AdminDao.getInstance().getAdminById(id);
    }

    public static AdminService getInstance() {
        return INSTANCE;
    }
}
