package com.Sk09Team.Patient.service;

import com.Sk09Team.Patient.external.response.DoctorResponse;
import com.Sk09Team.Patient.model.ConsultationRequest;
import com.Sk09Team.Patient.model.ConsultationResponse;
import com.Sk09Team.Patient.model.PatientRequest;
import com.Sk09Team.Patient.model.PatientResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientService {
    ResponseEntity<Long> reserveConsultation(ConsultationRequest consulationRequest);

    ResponseEntity<List<DoctorResponse>> getDoctorsByCityAndSpecialty(String city, String specialty);

    ResponseEntity<List<DoctorResponse>> getDoctorsByCity(String city);

    ResponseEntity<List<DoctorResponse>> getDoctorsBySpecialty(String specialty);

    ResponseEntity<List<DoctorResponse>> getAllDoctors();

    ResponseEntity<List<DoctorResponse>> getDoctorsByLastName(String lastName);

    DoctorResponse getDoctorByDoctorId(long doctorId);

    String updateProfile(long patientId, PatientRequest patientRequest);

    String registerPatient(PatientRequest patientRequest);

    PatientResponse getPatientByPatientId(long patientId);

    List<Long> deleteConsultation(long consultationtId, long patientId);

    List<ConsultationResponse> getAllConsultationsForPatient(long patientId);
}
