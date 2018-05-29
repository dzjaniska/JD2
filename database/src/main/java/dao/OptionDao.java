package dao;

import entity.Option;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OptionDao extends BaseDao<Long, Option> {

    private static final OptionDao INSTANCE = new OptionDao();

    public static OptionDao getInstance() {
        return INSTANCE;
    }
}
