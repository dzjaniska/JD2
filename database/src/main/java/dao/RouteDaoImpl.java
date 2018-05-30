package dao;

import dao.interfaces.RouteDao;
import entity.Route;
import org.springframework.stereotype.Repository;

@Repository
public class RouteDaoImpl extends BaseDaoImpl<Long, Route> implements RouteDao {
}
