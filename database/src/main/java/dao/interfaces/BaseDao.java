package dao.interfaces;

import entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<PK extends Serializable, T extends BaseEntity<PK>> {

    PK save(T object);

    List<T> findAll();

    T find(PK id);

    void update(T object);

    void delete(T object);
}
