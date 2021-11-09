package by.itacademy.javaenterprise.goralchuk.service;
import by.itacademy.javaenterprise.goralchuk.dao.DAO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Hospital {
    @Autowired
    private DAO<?> person;

    public List<?> selectPersons(){
        return person.findAllPersons();
    }
}
