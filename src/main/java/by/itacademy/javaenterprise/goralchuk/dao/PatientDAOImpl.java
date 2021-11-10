package by.itacademy.javaenterprise.goralchuk.dao;

import by.itacademy.javaenterprise.goralchuk.entity.Patient;
import by.itacademy.javaenterprise.goralchuk.entity.PatientSex;
import lombok.Data;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Data
public class PatientDAOImpl implements PatientDAO {
    private static final String SAVE_PATIENT = "" +
            "INSERT INTO patients " +
            "(patient_name," +
            "patient_surname," +
            "patient_sex,patient_birthday) " +
            "VALUES" +
            " (?,?,?,?)";
    private static final String GET_PATIENT = "" +
            "SELECT " +
            "patient_id, " +
            "patient_registration, " +
            "patient_name, " +
            "patient_surname, " +
            "patient_sex, " +
            "patient_birthday " +
            "FROM patients " +
            "WHERE patient_id = :id ";
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
            "ORDER BY patient_name ASC ";
    private static final String FIND_ALL_PATIENTS = "" +
            "SELECT " +
            "patient_id, " +
            "patient_registration, " +
            "patient_name, " +
            "patient_surname, " +
            "patient_sex, " +
            "patient_birthday " +
            "FROM patients ";

    private static final Logger logger = LoggerFactory.getLogger(PatientDAOImpl.class);

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Autowired
    public PatientDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Patient get(long id) {
        try {
            Map<String, Long> params = Map.of("id", id);
            return namedParameterJdbcTemplate.queryForObject(GET_PATIENT, params, new PatientMapper());
        } catch (Exception ex) {
            logger.error("" + new SQLException(ex.getMessage()));
        }
        return null;
    }

    @Override
    public void save(Patient patient) {
        try {
            jdbcTemplate.update(SAVE_PATIENT,
                    patient.getName(),
                    patient.getSurname(),
                    patient.getPatientSex().toString(),
                    patient.getBirthday()
            );
        } catch (Exception ex) {
            logger.error("" + new SQLException(ex.getMessage()));
        }
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
        List<Patient> patientList = null;
        try {
            patientList = jdbcTemplate
                    .query(FIND_ALL_PATIENTS, new PatientMapper());
        } catch (Exception ex) {
            logger.error("" + new SQLException(ex.getMessage()));
        }
        return patientList;
    }

    @Override
    public List<Patient> findBySexPatients(PatientSex patientSex) {
        List<Patient> patientList = null;
        try {
            patientList = jdbcTemplate
                    .query(FIND_LIMITS_PATIENTS, new PatientMapper(), String.valueOf(patientSex));
        } catch (Exception ex) {
            logger.error("" + new SQLException(ex.getMessage()));
        }
        return patientList;
    }
}
