package com.app.ClinicaMedica.Appointment;

import Util.FetchEntity;
import com.app.ClinicaMedica.Appointment.DTO.AppointmentCreateDTO;
import com.app.ClinicaMedica.Appointment.DTO.AppointmentDTO;
import com.app.ClinicaMedica.Appointment.DTO.AppointmentUpdateDTO;
import com.app.ClinicaMedica.Doctor.Doctor;
import com.app.ClinicaMedica.Doctor.DoctorRepository;
import com.app.ClinicaMedica.Patient.Patient;
import com.app.ClinicaMedica.Patient.PatientRepository;
import com.app.ClinicaMedica.Speciality.Speciality;
import com.app.ClinicaMedica.Speciality.SpecialityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final SpecialityRepository specialityRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public AppointmentService(
            AppointmentRepository appointmentRepository,
            DoctorRepository doctorRepository,
            SpecialityRepository specialityRepository,
            PatientRepository patientRepository
    ) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.specialityRepository = specialityRepository;
        this.patientRepository = patientRepository;
    }

    public List<AppointmentDTO> getAppointments() {
        return AppointmentDTO.converter(appointmentRepository.findAll());
    }

    public List<AppointmentDTO> getAppointmentsByDoctor(String crm) {
        return AppointmentDTO.converter(appointmentRepository.findByDoctorCrm(crm));
    }

    public List<AppointmentDTO> getAppointmentsByPatient(Long idPatient) {
        return AppointmentDTO.converter(appointmentRepository.findByPatientIdPatient(idPatient));
    }

    @Transactional
    public AppointmentDTO addNewAppointment(AppointmentCreateDTO form) {
        Doctor doctor = FetchEntity.fetchEntity(form.getCrm(), this.doctorRepository);
        Speciality speciality = FetchEntity.fetchEntity(form.getIdSpeciality(), this.specialityRepository, "Speciality");
        Patient patient = FetchEntity.fetchEntity(form.getIdPatient(), this.patientRepository, "Patient");

        Appointment appointment = form.converter(doctor, speciality, patient);
        this.appointmentRepository.save(appointment);

        return new AppointmentDTO(appointment);
    }

    @Transactional
    public AppointmentDTO updateAppointment(Long idAppointment, AppointmentUpdateDTO form) {
        Appointment appointment = FetchEntity.fetchEntity(idAppointment, this.appointmentRepository, "Appointment");
        Doctor doctor = FetchEntity.fetchEntity(form.getCrm(), this.doctorRepository);
        Speciality speciality = FetchEntity.fetchEntity(form.getIdSpeciality(), this.specialityRepository, "Speciality");
        Patient patient = FetchEntity.fetchEntity(form.getIdPatient(), this.patientRepository, "Patient");

        form.update(appointment, doctor, speciality, patient);
        this.appointmentRepository.save(appointment);

        return new AppointmentDTO(appointment);
    }

    @Transactional
    public AppointmentDTO deleteAppointment(Long idAppointment) {
        Appointment appointment = FetchEntity.fetchEntity(idAppointment, this.appointmentRepository, "Appointment");

        this.appointmentRepository.deleteById(idAppointment);

        return new AppointmentDTO(appointment);
    }
}
