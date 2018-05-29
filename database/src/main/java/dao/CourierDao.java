package dao;

import entity.Courier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourierDao extends BaseDao<Long, Courier> {

    private static final CourierDao INSTANCE = new CourierDao();

    public static CourierDao getInstance() {
        return INSTANCE;
    }
}
