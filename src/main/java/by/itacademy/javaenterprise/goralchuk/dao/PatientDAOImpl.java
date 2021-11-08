package by.itacademy.javaenterprise.goralchuk.dao;

import by.itacademy.javaenterprise.goralchuk.dao.utils.ConnectionUtils;
import by.itacademy.javaenterprise.goralchuk.entity.Patient;
import by.itacademy.javaenterprise.goralchuk.entity.PatientSex;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@AllArgsConstructor
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
            "patient_ID, " +
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
            "patient_ID, " +
            "patient_registration, " +
            "patient_name, " +
            "patient_surname, " +
            "patient_sex, " +
            "patient_birthday " +
            "FROM patients";

    private static final Logger logger = LoggerFactory.getLogger(PatientDAOImpl.class);

    private DataSource dataSource;

    @Override
    public Patient get(Serializable id) throws SQLException {
        return null;
    }

    @Override
    public void save(Patient patient) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = SAVE_PATIENT;
        Savepoint savepoint1 = null;
        if (patient != null) {
            try {
                connection = dataSource.getConnection();
                connection.setAutoCommit(false);
                savepoint1 = connection.setSavepoint("Savepoint One");

                String name = patient.getName();
                String surname = patient.getSurname();
                PatientSex patientSex = patient.getPatientSex();
                Date birthday = patient.getBirthday();

                statement = connection.prepareStatement(sql);

                statement.setString(1, name);
                statement.setString(2, surname);
                statement.setString(3, String.valueOf(patientSex));
                statement.setDate(4, (java.sql.Date) birthday);

                statement.executeUpdate();
                connection.commit();
                logger.info("Patient added to the table successfully!");
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                logger.error(ex.toString());
                ConnectionUtils.rollbackTransaction(connection, savepoint1);
            } finally {
                ConnectionUtils.closePrepareStatement(statement);
                ConnectionUtils.closeConnection(connection);
            }
        }
    }

    @Override
    public void update(Patient patients) throws SQLException {

    }

    @Override
    public int delete(Serializable id) throws SQLException {
        return 0;
    }

    @Override
    public List<Patient> findAllPersons() {
        List<Patient> patients = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = FIND_ALL_PATIENTS;
        Savepoint savepoint1 = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            savepoint1 = connection.setSavepoint("Savepoint One");
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                patients.add(getPatient(resultSet));
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            logger.error(ex.toString());
            ConnectionUtils.rollbackTransaction(connection, savepoint1);
        } finally {
            ConnectionUtils.closeResultSet(resultSet);
            ConnectionUtils.closePrepareStatement(statement);
            ConnectionUtils.closeConnection(connection);
        }
        return patients;
    }

    @Override
    public List<Patient> findBySexPatients(PatientSex patientSex) {
        List<Patient> patients = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = FIND_LIMITS_PATIENTS;
        Savepoint savepoint1 = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            savepoint1 = connection.setSavepoint("Savepoint One");
            statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(patientSex));
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                patients.add(getPatient(resultSet));
            }

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            logger.error(ex.toString());
            ConnectionUtils.rollbackTransaction(connection, savepoint1);
        } finally {
            ConnectionUtils.closeResultSet(resultSet);
            ConnectionUtils.closePrepareStatement(statement);
            ConnectionUtils.closeConnection(connection);
        }
        return patients;
    }

    private Patient getPatient(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            Long id = resultSet.getLong("patient_ID");
            Date registrationDate = resultSet.getTimestamp("patient_registration");
            String name = resultSet.getString("patient_name");
            String surname = resultSet.getString("patient_surname");
            PatientSex patientSex = PatientSex.valueOf(resultSet.getString("patient_sex"));
            Date birthday = resultSet.getDate("patient_birthday");

            Patient patient = new Patient();
            patient.setId(id);
            patient.setRegistrationDate(registrationDate);
            patient.setName(name);
            patient.setSurname(surname);
            patient.setPatientSex(patientSex);
            patient.setBirthday(birthday);

            return patient;
        }
        return null;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
