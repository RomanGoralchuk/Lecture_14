package by.itacademy.javaenterprise.goralchuk.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    T get(long id);

    void save(T t);

    void update(T t) throws SQLException;

    int delete(Serializable id) throws SQLException;

    List<T> findAllPersons();
}
