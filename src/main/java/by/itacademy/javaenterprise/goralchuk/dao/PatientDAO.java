package by.itacademy.javaenterprise.goralchuk.dao;

import by.itacademy.javaenterprise.goralchuk.entity.Patient;
import by.itacademy.javaenterprise.goralchuk.entity.PatientSex;

public interface PatientDAO extends DAO<Patient> {

    Object findBySexPatients(PatientSex patientSex);
}
