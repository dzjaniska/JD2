package dao;

import entity.Courier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourieDaoImpl extends BaseBaseDao<Long, Courier> {

    private static final CourieDaoImpl INSTANCE = new CourieDaoImpl();

    public static CourieDaoImpl getInstance() {
        return INSTANCE;
    }
}
