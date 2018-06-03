package dao;

import dao.interfaces.OptionDao;
import entity.Option;
import org.springframework.stereotype.Repository;

@Repository
public class OptionDaoImpl extends BaseDaoImpl<Long, Option> implements OptionDao {
}
