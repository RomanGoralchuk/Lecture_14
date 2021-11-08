package by.itacademy.javaenterprise.goralchuk.dao;

import by.itacademy.javaenterprise.goralchuk.entity.Patient;
import by.itacademy.javaenterprise.goralchuk.entity.PatientSex;

import java.util.List;

public interface PatientDAO extends DAO<Patient> {

    List<Patient> findBySexPatients(PatientSex patientSex);
}
