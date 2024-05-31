package com.app.ClinicaMedica.Doctor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Doctor {
    @Id
    private String crm;

    @NotEmpty(message = "Doctor Name may not be empty")
    private String doctorName;

    @NotEmpty(message = "Doctor Phone may not be empty")
    private String doctorPhone;

    @Positive(message = "Percentage must be a positive number")
    @NotNull(message = "Percentage may not be empty")
    private Float percentage;

    public Doctor() {}

    public Doctor(String crm, String doctorName, String doctorPhone, Float percentage) {
        crm = crm;
        doctorName = doctorName;
        doctorPhone = doctorPhone;
        percentage = percentage;
    }

    public Doctor(String doctorName, String doctorPhone, Float percentage) {
        doctorName = doctorName;
        doctorPhone = doctorPhone;
        percentage = percentage;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "crm='" + crm + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", doctorPhone='" + doctorPhone + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
