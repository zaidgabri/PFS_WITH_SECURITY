package com.Sk09Team.Doctor.service;

import com.Sk09Team.Doctor.entity.Doctor;
import com.Sk09Team.Doctor.entity.Patient;
import com.Sk09Team.Doctor.external.client.CosnultationClient;
import com.Sk09Team.Doctor.model.ConsultationResponseForDoctor;
import com.Sk09Team.Doctor.model.DoctorFullProfileRequest;
import com.Sk09Team.Doctor.model.DoctorRequest;
import com.Sk09Team.Doctor.model.DoctorResponse;
import com.Sk09Team.Doctor.repository.DoctorRepository;
import com.Sk09Team.Doctor.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.Sk09Team.Doctor.entity.Role.DOCTOR;

@Service
public class DoctorServiceImpl implements  DoctorService {
    @Autowired
    private CosnultationClient consultationClient;
    @Autowired
    private DoctorRepository doctorRepository ;
   @Autowired
    PatientRepository patientRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;


    @Override
    public ResponseEntity<Long> approveConsultation(long consultationId, long doctorId) {
        return consultationClient.approveConsultation(consultationId, doctorId);
    }
    @Override
   public List<DoctorResponse> getDoctorsByCity(String city) {
         List<Doctor> doctors = doctorRepository.findByCity(city);
         return getDoctorResponses(doctors);
    }
    @Override
    public List<DoctorResponse> getDoctorsBySpecialty(String specialty) {
        List<Doctor> doctors = doctorRepository.findBySpecialty(specialty);
        return getDoctorResponses(doctors);
    }
    @Override
    public List<DoctorResponse> getDoctorsByLastName(String lastName) {
        List<Doctor> doctors = doctorRepository.findByLastName(lastName);
        return getDoctorResponses(doctors);
    }


    @Override
    public ResponseEntity<List<ConsultationResponseForDoctor>> getAllConsultationsForDoctor(long doctorId) {
        List<ConsultationResponseForDoctor> consultations = consultationClient.getAllConsultationsForDoctor(doctorId).getBody();

        assert consultations != null;

        List<Patient> patients = patientRepository.findAll();

        for (ConsultationResponseForDoctor consultation : consultations) {
            String patientCIN = consultation.getPatientCIN();
            boolean patientExists = false;

            for (Patient patient : patients) {
                if (patientCIN.equals(patient.getPatientCIN())) {
                    patientExists = true;
                    break;
                }
            }

            if (!patientExists) {
                // Patient does not exist, create a new patient entity
                Patient newPatient = Patient.builder()
                        .doctorId(doctorId)
                        .patientCIN(patientCIN)
                        .patientFirstName(consultation.getPatientFirstName())
                        .patientLastName(consultation.getPatientLastName())
                        .patientGender(consultation.getPatientGender())
                        .patientPhone(consultation.getPatientPhone())
                        .patientbirthDate(consultation.getPatientbirthDate())
                        .patientPostCode(consultation.getPatientPostCode())
                        .patientEmail(consultation.getPatientEmail())
                        .patientCity(consultation.getPatientCity())
                        .patientAddress(consultation.getPatientAddress())
                        .build();

                patientRepository.save(newPatient);
            }
        }

        return ResponseEntity.ok(consultations);
    }

    @Override
    public List<Patient> getAllPatientsByDoctorId(long doctorId) {
        return patientRepository.findByDoctorId(doctorId);
    }

    @Override
    public void updateDoctor(long doctorId, DoctorFullProfileRequest doctorRequest) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + doctorId));

        // Update doctor's personal data using the builder
        Doctor updatedDoctor = Doctor.builder()
                .doctorId(doctor.getDoctorId())
                .firstName(doctorRequest.getFirstName())
                .lastName(doctorRequest.getLastName())
                .password(passwordEncoder.encode(doctorRequest.getPassword()))
                .address(doctorRequest.getAddress())
                .email(doctorRequest.getEmail())
                .doctorPhone(doctorRequest.getPhone())
                .city(doctorRequest.getCity())
                .postCode(doctorRequest.getPostCode())
                .specialty(doctorRequest.getSpecialty())
                .location(doctorRequest.getLocation())
                .diplomas(doctorRequest.getDiplomas())
                .place(doctorRequest.getPlace())
                .paymentMode(doctorRequest.getPaymentMode())
                .languages(doctorRequest.getLanguages())
                .calendar(doctorRequest.getCalendar())
                .role(DOCTOR)
                .build();

        doctorRepository.save(updatedDoctor);
    }



    @Override
   public List<DoctorResponse> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return getDoctorResponses(doctors);
    }

    @Override
   public List<DoctorResponse> getDoctorsByCityAndSpecialty(String city, String specialty) {
        List<Doctor> doctors = doctorRepository.findByCityAndSpecialty(city, specialty);
        return getDoctorResponses(doctors);

    }

    @Override
    public long registerDoctor(DoctorRequest doctorRequest) {
        Doctor doctor=Doctor.builder()
                .firstName(doctorRequest.getFirstName())
                .lastName(doctorRequest.getLastName())
                .city(doctorRequest.getCity())
                .specialty(doctorRequest.getSpecialty())
//                .postCode(doctorRequest.getPostCode())
//                .address(doctorRequest.getAddress())
                .password(doctorRequest.getPassword())
                .build();

        doctorRepository.save(doctor);
        return doctor.getDoctorId();
    }


    private List<DoctorResponse> getDoctorResponses(List<Doctor> doctors) {
        List<DoctorResponse> doctorsResponses = new ArrayList<>();
        for (Doctor doctor : doctors) {
           DoctorResponse doctorResponse = DoctorResponse.builder()
                   .doctorEmail(doctor.getEmail())
                   .doctorPhone(doctor.getDoctorPhone())
                   .address(doctor.getAddress())
                   .place(doctor.getPlace())
                   .city(doctor.getCity())
                   .location(doctor.getLocation())
                   .firstName(doctor.getFirstName())
                   .languages(doctor.getLanguages())
                   .diplomas(doctor.getDiplomas())
                   .lastName(doctor.getLastName())
                   .paymentMode(doctor.getPaymentMode())
                   .specialty(doctor.getSpecialty())
                   .calendar(doctor.getCalendar())
                   .doctorId(doctor.getDoctorId())
                   .build();
            doctorsResponses.add(doctorResponse);
        }
        return doctorsResponses;
    }
    @Override
    public DoctorResponse getDoctorByDoctorId(long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + doctorId));

        // Map the patient entity to PatientResponse

        return DoctorResponse.builder()
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .specialty(doctor.getSpecialty())

                .paymentMode(doctor.getPaymentMode())
                .location(doctor.getLocation())
                .doctorPhone(doctor.getDoctorPhone())
                .doctorEmail(doctor.getEmail())
                .diplomas(doctor.getDiplomas())
                .city(doctor.getCity())
                .address(doctor.getAddress())
                .languages(doctor.getLanguages())
                .calendar(doctor.getCalendar())
                .place(doctor.getPlace())

                .build();
    }

}
