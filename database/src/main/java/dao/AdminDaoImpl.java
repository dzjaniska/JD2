package dao;

import dao.interfaces.AdminDao;
import entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl extends BaseDaoImpl<Long, Admin> implements AdminDao {

}
