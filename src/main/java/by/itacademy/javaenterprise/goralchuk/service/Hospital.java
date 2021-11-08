package by.itacademy.javaenterprise.goralchuk.service;
import by.itacademy.javaenterprise.goralchuk.dao.DAO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Data
@Component
public class Hospital {
    @Autowired
    @Qualifier("doctorBean")
    private DAO<?> person;

    public List<?> selectPersons(){
        return person.findAllPersons();
    }
}
