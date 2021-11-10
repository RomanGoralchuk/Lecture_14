package by.itacademy.javaenterprise.goralchuk.dao;

import by.itacademy.javaenterprise.goralchuk.entity.Patient;
import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.junit.*;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

import static org.junit.Assert.*;

public class PatientDAOImplTest {
    private static MariaDBContainer mariaDBContainer;
    private static BasicDataSource dataSource;
    private PatientDAOImpl patientDAO;


    @BeforeClass
    public static void beforeClass() throws Exception {
        mariaDBContainer = new MariaDBContainer("mariadb:10.1.16");
        mariaDBContainer.start();
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(mariaDBContainer.getDriverClassName());
        dataSource.setUrl(mariaDBContainer.getJdbcUrl());
        dataSource.setUsername(mariaDBContainer.getContainerName());
        dataSource.setPassword(mariaDBContainer.getPassword());

        Flyway flyway = Flyway.configure().dataSource(dataSource).locations("classpath:sql").load();
        flyway.migrate();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        mariaDBContainer.stop();
    }

    @Before
    public void setUp() throws Exception {
        patientDAO = new PatientDAOImpl(patientDAO.getJdbcTemplate(), patientDAO.getNamedParameterJdbcTemplate());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void whenLookingForAPatientById() {
        long id = 2;
        Patient patientTest = patientDAO.get(id);
        assertEquals(id, patientTest);
    }

    @Test
    public void save() {
    }

    @Test
    public void findAllPersons() {
    }

    @Test
    public void findBySexPatients() {
    }
}