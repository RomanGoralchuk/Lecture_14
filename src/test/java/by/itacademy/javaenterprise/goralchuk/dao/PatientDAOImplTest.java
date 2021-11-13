package by.itacademy.javaenterprise.goralchuk.dao;

import by.itacademy.javaenterprise.goralchuk.entity.Patient;
import by.itacademy.javaenterprise.goralchuk.entity.PatientSex;
import org.flywaydb.core.Flyway;
import org.junit.*;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatcher;
import org.junit.rules.TestWatchman;
import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.Assert.*;

public class PatientDAOImplTest {
    private static PatientDAOImpl patientDAO;
    private static DataSource dataSource;
    private static final Logger logger = LoggerFactory.getLogger(PatientDAOImplTest.class);

    @Rule
    public TestWatcher watchman= new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            logger.info("Test failed: " + description);
        }
        @Override
        protected void succeeded(Description description) {
            logger.info("Test successes: " + description);
        }
    };

    @BeforeClass
    public static void beforeClass() throws Exception {
        dataSource = new DriverManagerDataSource(
                "jdbc:mariadb://127.0.0.1:3306/hospital?useUnicode=true&characterEncoding=UTF-8",
                "user",
                "userpass");
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:database/migration")
                .load();
        flyway.migrate();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        patientDAO = new PatientDAOImpl(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        dataSource.getConnection().close();
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void whenLookingForAPatientById() {
        long patientFindId = 2;
        Patient patientDaoTest = patientDAO.get(patientFindId);
        long patientRealId = patientDaoTest.getId();
        assertEquals(patientRealId, patientFindId);
    }

    @Test
    public void whenWeSaveAPatientToTheDataBase() {
        Patient patientDaoNew = new Patient(
                "TestName",
                "TestSurname",
                PatientSex.M,
                java.sql.Date.valueOf("1111-11-11"));

        List<Patient> listPatientBefore = patientDAO.findAllPersons();
        int numberOfRecordsBefore = listPatientBefore.size();

        patientDAO.save(patientDaoNew);

        List<Patient> listPatientAfter = patientDAO.findAllPersons();
        int numberOfRecordsAfter = listPatientAfter.size();

        assertEquals(numberOfRecordsAfter, numberOfRecordsBefore + 1);
    }

    @Test
    public void whenWeGetAListOfAllPersons() {
        List<Patient> listPatientTest = patientDAO.findAllPersons();
        assertNotNull(listPatientTest);
    }

    @Test
    public void whenWeFindPatientsBySex() {
        List<Patient> listPatientTest = patientDAO.findBySexPatients(PatientSex.M);
        assertNotNull(listPatientTest);
    }
}