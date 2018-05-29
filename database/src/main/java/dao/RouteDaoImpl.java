package dao;

import entity.Route;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RouteDaoImpl extends BaseBaseDao<Long, Route> {

    private static final RouteDaoImpl INSTANCE = new RouteDaoImpl();

    public static RouteDaoImpl getInstance() {
        return INSTANCE;
    }
}
