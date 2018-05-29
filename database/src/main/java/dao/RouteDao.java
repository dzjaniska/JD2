package dao;

import entity.Route;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RouteDao extends BaseDao<Long, Route> {

    private static final RouteDao INSTANCE = new RouteDao();

    public static RouteDao getInstance() {
        return INSTANCE;
    }
}
