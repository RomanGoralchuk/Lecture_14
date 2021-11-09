package by.itacademy.javaenterprise.goralchuk.dao;

import by.itacademy.javaenterprise.goralchuk.entity.Patient;
import by.itacademy.javaenterprise.goralchuk.entity.PatientSex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PatientMapper implements RowMapper<Patient> {

    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("patient_id");
        Date registrationDate = rs.getTimestamp("patient_registration");
        String name = rs.getString("patient_name");
        String surname = rs.getString("patient_surname");
        PatientSex patientSex = PatientSex.valueOf(rs.getString("patient_sex"));
        Date birthday = rs.getDate("patient_birthday");

        Patient patient = new Patient();
        patient.setId(id);
        patient.setRegistrationDate(registrationDate);
        patient.setName(name);
        patient.setSurname(surname);
        patient.setPatientSex(patientSex);
        patient.setBirthday(birthday);
        return patient;
    }
}
