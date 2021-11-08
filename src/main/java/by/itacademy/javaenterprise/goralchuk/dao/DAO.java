package by.itacademy.javaenterprise.goralchuk.dao;

import by.itacademy.javaenterprise.goralchuk.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    T get(Serializable id) throws SQLException;

    void save(T t);

    void update(T t) throws SQLException;

    int delete(Serializable id) throws SQLException;

    List<T> findAllPersons();
}
