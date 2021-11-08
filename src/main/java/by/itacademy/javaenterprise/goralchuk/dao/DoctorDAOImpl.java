package by.itacademy.javaenterprise.goralchuk.dao;

import by.itacademy.javaenterprise.goralchuk.dao.utils.ConnectionUtils;
import by.itacademy.javaenterprise.goralchuk.entity.Doctor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component("doctorBean")
public class DoctorDAOImpl implements DoctorDAO {
    private static final String SAVE_DOCTOR = "" +
            "INSERT INTO doctors " +
            "(doc_name, " +
            "doc_surname, " +
            "doc_specialization)" +
            "VALUES" +
            " (?,?,?)";

    private static final String FIND_ALL_DOCTORS = "" +
            "SELECT " +
            "doc_ID, " +
            "doc_name, " +
            "doc_surname, " +
            "doc_specialization " +
            "FROM doctors";

    private static final Logger logger = LoggerFactory.getLogger(DoctorDAOImpl.class);

    private DataSource dataSource;

    @Override
    public Doctor get(Serializable id) throws SQLException {
        return null;
    }

    @Override
    public void save(Doctor doctor) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = SAVE_DOCTOR;
        Savepoint savepoint1 = null;
        if (doctor != null) {
            try {
                connection = dataSource.getConnection();
                connection.setAutoCommit(false);
                savepoint1 = connection.setSavepoint("Savepoint One");

                String name = doctor.getName();
                String surname = doctor.getSurname();
                String specialization = doctor.getSpecialization();

                statement = connection.prepareStatement(sql);

                statement.setString(1, name);
                statement.setString(2, surname);
                statement.setString(3, specialization);

                statement.executeUpdate();
                connection.commit();
                logger.info("Doctor added to the table successfully!");
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
    public void update(Doctor doctor) throws SQLException {

    }

    @Override
    public int delete(Serializable id) throws SQLException {
        return 0;
    }

    @Override
    public List<Doctor> findAllPersons() {
        List<Doctor> doctors = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = FIND_ALL_DOCTORS;
        Savepoint savepoint1 = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            savepoint1 = connection.setSavepoint("Savepoint One");
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                doctors.add(getDoctor(resultSet));
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
        return doctors;
    }

    private Doctor getDoctor(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            Long id = resultSet.getLong("doc_ID");
            String name = resultSet.getString("doc_name");
            String surname = resultSet.getString("doc_surname");
            String specialization = resultSet.getString("doc_specialization");

            Doctor doctor = new Doctor();
            doctor.setId(id);
            doctor.setName(name);
            doctor.setSurname(surname);
            doctor.setSpecialization(specialization);
            return doctor;
        }
        return null;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
