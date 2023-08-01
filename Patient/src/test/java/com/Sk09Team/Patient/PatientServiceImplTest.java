package com.Sk09Team.Patient;

//package com.Sk09Team.Patient.service;

import com.Sk09Team.Patient.entity.Patient;
import com.Sk09Team.Patient.external.client.ConsultationClient;
import com.Sk09Team.Patient.external.client.DoctorClient;
import com.Sk09Team.Patient.external.response.DoctorResponse;
import com.Sk09Team.Patient.model.ConsultationRequest;
import com.Sk09Team.Patient.model.PatientRequest;
import com.Sk09Team.Patient.model.PatientResponse;
import com.Sk09Team.Patient.patientRepository.PatientRepository;
import com.Sk09Team.Patient.service.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PatientServiceImplTest {

    @Mock
    private ConsultationClient consultationClient;

    @Mock
    private DoctorClient doctorClient;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;




    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void reserveConsultation_shouldReturnResponseEntity() {
        // Arrange
        ConsultationRequest consultationRequest = new ConsultationRequest();
        ResponseEntity<Long> expectedResponse = ResponseEntity.ok(123L);
        when(consultationClient.reserveConsultation(consultationRequest)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<Long> response = patientService.reserveConsultation(consultationRequest);

        // Assert
        assertEquals(expectedResponse, response);
    }

    @Test
    void getDoctorsByCityAndSpecialty_shouldReturnResponseEntity() {
        // Arrange
        String city = "Test City";
        String specialty = "Test Specialty";
        ResponseEntity<List<DoctorResponse>> expectedResponse = ResponseEntity.ok(new ArrayList<>());
        when(doctorClient.getDoctorsByCityAndSpecialty(city, specialty)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<List<DoctorResponse>> response = patientService.getDoctorsByCityAndSpecialty(city, specialty);

        // Assert
        assertEquals(expectedResponse, response);
    }

    // Add tests for other methods

    @Test
    void updateProfile_shouldUpdatePatientProfileAndReturnSuccessMessage() {
        // Arrange
        long patientId = 52L;
        PatientRequest patientRequest = new PatientRequest();
        patientRequest.setFirstName("John");
        patientRequest.setLastName("Doe");
        patientRequest.setGender("Male");
        // Set other properties as needed

        Patient existingPatient = new Patient();
        existingPatient.setPatientId(patientId);

        when(patientRepository.findById(patientId)).thenReturn(java.util.Optional.of(existingPatient));
        when(patientRepository.save(any(Patient.class))).thenReturn(existingPatient);

        // Act
        String result = patientService.updateProfile(patientId, patientRequest);

        // Assert
        assertEquals("Patient profile updated successfully", result);
        assertEquals(patientRequest.getFirstName(), existingPatient.getFirstName());
        assertEquals(patientRequest.getLastName(), existingPatient.getLastName());
        assertEquals(patientRequest.getGender(), existingPatient.getGender());
        // Assert other properties as needed
        verify(patientRepository, times(1)).findById(patientId);
        verify(patientRepository, times(1)).save(existingPatient);
    }

    @Test
    void registerPatient_shouldSavePatientAndReturnSuccessMessage() {
        // Arrange
        PatientRequest patientRequest = new PatientRequest();
        patientRequest.setFirstName("John");
        patientRequest.setLastName("Doe");
        patientRequest.setGender("Male");
        // Set other properties as needed

        // Act
        String result = patientService.registerPatient(patientRequest);

        // Assert
        assertEquals("Patient registered successfully.", result);
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    void getPatientByPatientId_shouldReturnPatientResponse() {
        // Arrange
        long patientId = 1L;
        Patient patient = new Patient();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setGender("Male");
        // Set other properties as needed

        when(patientRepository.findById(patientId)).thenReturn(java.util.Optional.of(patient));

        // Act
        PatientResponse response = patientService.getPatientByPatientId(patientId);

        // Assert
        assertEquals(patient.getFirstName(), response.getFirstName());
        assertEquals(patient.getLastName(), response.getLastName());
        assertEquals(patient.getGender(), response.getGender());
        // Assert other properties as needed
        verify(patientRepository, times(1)).findById(patientId);
    }

    @Test
    void getDoctorByDoctorId_shouldReturnDoctorResponse() {
        // Arrange
        long doctorId = 1L;
        DoctorResponse expectedResponse = new DoctorResponse();
        when(doctorClient.getDoctorByDoctorId(doctorId)).thenReturn(expectedResponse);

        // Act
        DoctorResponse response = patientService.getDoctorByDoctorId(doctorId);

        // Assert
        assertEquals(expectedResponse, response);
        verify(doctorClient, times(1)).getDoctorByDoctorId(doctorId);
    }
}

