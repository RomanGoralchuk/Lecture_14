package by.itacademy.javaenterprise.goralchuk.spring;

import by.itacademy.javaenterprise.goralchuk.dao.PatientDAO;
import by.itacademy.javaenterprise.goralchuk.dao.PatientDAOImpl;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:database.properties")
@ComponentScan("by.itacademy.javaenterprise.goralchuk")
public class SpringConfig {

    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.maxIdle}")
    private int maxIdle;
    @Value("${jdbc.minIdle}")
    private int minIdle;

    @Bean
    public PatientDAO patientBean(){
        return new PatientDAOImpl(dataSourceBean());
    }

    @Bean (destroyMethod = "close")
    public DataSource dataSourceBean(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driverClassName);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setMaxIdle(maxIdle);
        basicDataSource.setMinIdle(minIdle);
        return basicDataSource;
    }

}
