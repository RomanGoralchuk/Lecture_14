package by.itacademy.javaenterprise.goralchuk;

import by.itacademy.javaenterprise.goralchuk.dao.*;
import by.itacademy.javaenterprise.goralchuk.entity.Doctor;
import by.itacademy.javaenterprise.goralchuk.entity.Patient;
import by.itacademy.javaenterprise.goralchuk.entity.PatientSex;
import by.itacademy.javaenterprise.goralchuk.service.Hospital;
import by.itacademy.javaenterprise.goralchuk.spring.SpringConfig;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class MainTest {

    private static final Logger logger = LoggerFactory.getLogger(MainTest.class);

    public static void main(String[] args) {

        Flyway flyway = Flyway.configure().loadDefaultConfigurationFiles().load();
        flyway.migrate();

        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext(SpringConfig.class);

        PatientDAO patientsDAO = context.getBean("patientBean", PatientDAOImpl.class);
        logger.info("Test response to the request: " + patientsDAO.findAllPersons());
/*
        patientsDAO.save(new Patient("Cindy", "Crawford", PatientSex.M, java.sql.Date.valueOf("1966-02-20")));
*/
        logger.info("Test response to the request: " + patientsDAO.findBySexPatients(PatientSex.F));
        logger.info("Test response to the request: " + patientsDAO.findBySexPatients(PatientSex.M));

        DoctorDAO doctorDAO = context.getBean("doctorBean", DoctorDAOImpl.class);
/*
        doctorDAO.save(new Doctor("Yuri", "Zhivago", "philosopher"));
*/
        logger.info("Test response to the request: " + doctorDAO.findAllPersons());

        context.close();

        AnnotationConfigApplicationContext context2 = new
                AnnotationConfigApplicationContext(SpringConfig.class);

        DAO<Doctor> doctor = context2.getBean("doctorBean", DoctorDAOImpl.class);
        logger.info("Example of response to the request: " + doctor.findAllPersons());
        DAO<Patient> patient = context2.getBean("patientBean", PatientDAOImpl.class);
        logger.info("Example of response to the request: " + patient.findAllPersons());
        Hospital hospital = context2.getBean("hospital", Hospital.class);
        logger.info("Example of Qualifier " + hospital.selectPersons());

        context2.close();
    }
}
