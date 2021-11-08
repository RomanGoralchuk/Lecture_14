package by.itacademy.javaenterprise.goralchuk.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
public class Doctor {
    private Long id;
    private String name;
    private String surname;
    private String specialization;

    public Doctor(String name, String surname, String specialization) {
        this.name = name;
        this.surname = surname;
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "\n Doctor: " +
                "id=" + id +
                ", name='" + name + "'" +
                ", surname='" + surname + "'" +
                ", specialization='" + specialization;
    }
}
