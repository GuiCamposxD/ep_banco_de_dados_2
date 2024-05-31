package com.app.ClinicaMedica.Doctor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Doctor {
    @Id
    private String Crm;
    private String DoctorName;
    private String DoctorPhone;
    private Float Percentage;

    public Doctor() {}

    public Doctor(String crm, String doctorName, String doctorPhone, Float percentage) {
        Crm = crm;
        DoctorName = doctorName;
        DoctorPhone = doctorPhone;
        Percentage = percentage;
    }

    public Doctor(String doctorName, String doctorPhone, Float percentage) {
        DoctorName = doctorName;
        DoctorPhone = doctorPhone;
        Percentage = percentage;
    }

    public String getCrm() {
        return Crm;
    }

    public void setCrm(String crm) {
        Crm = crm;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getDoctorPhone() {
        return DoctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        DoctorPhone = doctorPhone;
    }

    public Float getPercentage() {
        return Percentage;
    }

    public void setPercentage(Float percentage) {
        Percentage = percentage;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "Crm='" + Crm + '\'' +
                ", DoctorName='" + DoctorName + '\'' +
                ", DoctorPhone='" + DoctorPhone + '\'' +
                ", Percentage=" + Percentage +
                '}';
    }
}
