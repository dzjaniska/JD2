package dao;

import entity.Option;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OptionDaoImpl extends BaseBaseDao<Long, Option> {

    private static final OptionDaoImpl INSTANCE = new OptionDaoImpl();

    public static OptionDaoImpl getInstance() {
        return INSTANCE;
    }
}
