package service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminService {

    private static final AdminService INSTANCE = new AdminService();

    public static AdminService getInstance() {
        return INSTANCE;
    }
}
