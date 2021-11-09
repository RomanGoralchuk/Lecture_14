package by.itacademy.javaenterprise.goralchuk;

import by.itacademy.javaenterprise.goralchuk.dao.PatientDAO;
import by.itacademy.javaenterprise.goralchuk.dao.PatientDAOImpl;
import by.itacademy.javaenterprise.goralchuk.entity.Patient;
import by.itacademy.javaenterprise.goralchuk.entity.PatientSex;
import by.itacademy.javaenterprise.goralchuk.spring.SpringConfig;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainTest {

    private static final Logger logger = LoggerFactory.getLogger(MainTest.class);

    public static void main(String[] args) {

        Flyway flyway = Flyway.configure()
                .loadDefaultConfigurationFiles()
                .load();
        flyway.migrate();

        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext(SpringConfig.class);

        PatientDAO patientsDAO = context.getBean("patientBean", PatientDAOImpl.class);

        logger.info("Test response to the request: " + patientsDAO.get(2));
        logger.info("Test response to the request: " + patientsDAO.findAllPersons());
        patientsDAO.save(new Patient("Santa", "Claus", PatientSex.M, java.sql.Date.valueOf("1920-12-24")));
        logger.info("Test response to the request: " + patientsDAO.findBySexPatients(PatientSex.M));

        context.close();
    }
}
