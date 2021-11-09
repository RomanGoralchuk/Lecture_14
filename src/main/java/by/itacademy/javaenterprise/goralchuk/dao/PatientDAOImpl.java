package by.itacademy.javaenterprise.goralchuk.dao;

import by.itacademy.javaenterprise.goralchuk.dao.utils.ConnectionUtils;
import by.itacademy.javaenterprise.goralchuk.entity.Patient;
import by.itacademy.javaenterprise.goralchuk.entity.PatientSex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PatientDAOImpl implements PatientDAO {
    private static final String SAVE_PATIENT = "" +
            "INSERT INTO patients " +
            "(patient_name," +
            "patient_surname," +
            "patient_sex,patient_birthday) " +
            "VALUES" +
            " (?,?,?,?)";
    private static final String FIND_LIMITS_PATIENTS = "" +
            "SELECT " +
            "patient_id, " +
            "patient_registration, " +
            "patient_name, " +
            "patient_surname, " +
            "patient_sex, " +
            "patient_birthday " +
            "FROM patients " +
            "WHERE patient_sex = ? " +
            "ORDER BY patient_name ASC" +
            "";
    private static final String FIND_ALL_PATIENTS = "" +
            "SELECT " +
            "patient_id, " +
            "patient_registration, " +
            "patient_name, " +
            "patient_surname, " +
            "patient_sex, " +
            "patient_birthday " +
            "FROM patients";

    private static final Logger logger = LoggerFactory.getLogger(PatientDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PatientDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Patient get(Serializable id) throws SQLException {
        return null;
    }

    @Override
    public void save(Patient patient) {
        jdbcTemplate.update(SAVE_PATIENT,
                patient.getName(),
                patient.getSurname(),
                patient.getPatientSex().toString(),
                patient.getBirthday()
        );
    }

    @Override
    public void update(Patient patient) throws SQLException {

    }

    @Override
    public int delete(Serializable id) throws SQLException {
        return 0;
    }

    @Override
    public List<Patient> findAllPersons() {
            return jdbcTemplate
                    .query(FIND_ALL_PATIENTS, new PatientMapper());
    }

    @Override
    public List<Patient> findBySexPatients(PatientSex patientSex) {
        return jdbcTemplate
                .query(FIND_LIMITS_PATIENTS,new PatientMapper(), String.valueOf(patientSex));
    }
}
