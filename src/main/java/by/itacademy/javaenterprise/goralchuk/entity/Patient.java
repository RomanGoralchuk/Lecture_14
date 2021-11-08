package by.itacademy.javaenterprise.goralchuk.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@NoArgsConstructor
public class Patient {
    private Long id;
    private Date registrationDate;
    private String name;
    private String surname;
    private PatientSex patientSex;
    private Date birthday;

    public Patient(String name, String surname, PatientSex patientSex, Date birthday) {
        this.name = name;
        this.surname = surname;
        this.patientSex = patientSex;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "\n Patient: " +
                "id=" + id +
                ", registration=" + registrationDate +
                ", name='" + name + "'" +
                ", surname='" + surname + "'" +
                ", sex=" + patientSex.getCode() +
                ", birthday=" + birthday;
    }
}

