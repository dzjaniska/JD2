package dao;

import dao.interfaces.CourierDao;
import entity.Courier;
import org.springframework.stereotype.Repository;

@Repository
public class CourieDaoImpl extends BaseDaoImpl<Long, Courier> implements CourierDao {

}
