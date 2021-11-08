package by.itacademy.javaenterprise.goralchuk.entity;

public enum PatientSex {
    M("male"),
    F("female");
    private String code;

    PatientSex(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
