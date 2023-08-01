package com.Sk09Team.Patient.controller;

import com.Sk09Team.Patient.model.*;
import com.Sk09Team.Patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/patient")
@PreAuthorize("hasRole('PATIENT')")
public class PatientController {
    @Autowired
    PatientService patientService;


    @PreAuthorize("hasAuthority('patient:read')")
    @GetMapping("/test")
    public String consultationServiceFallback() {
        return "it works";

    }


    @PostMapping("/reserve")
    public ResponseEntity<Long> reserveConsultation(@RequestBody ConsultationRequest consulationRequest){
        return patientService.reserveConsultation(consulationRequest);
    }

    @PreAuthorize("hasAuthority('patient:update')")
    @PutMapping("/profile/updateProfile/{patientId}")
    public ResponseEntity<UpdateProfileResponse>updateProfile(@PathVariable("patientId")  long patientId , @RequestBody PatientRequest patientRequest){

        return new ResponseEntity<>(UpdateProfileResponse.builder().message(patientService.updateProfile(patientId,patientRequest)).build(),HttpStatus.OK);
    }




    @PostMapping("/register")
    public ResponseEntity<PatientRegistrationResponse> registerPatient(@RequestBody PatientRequest patientRequest) {
        String result = patientService.registerPatient(patientRequest);
        return ResponseEntity.ok(PatientRegistrationResponse.builder().message(result).build());
    }


    @DeleteMapping("/{consultationId}/{patientId}/delete")
    public ResponseEntity<List<Long>>deleteConsultationForPatient(@PathVariable("consultationId") long  consultationtId ,@PathVariable("patientId") long  patientId){
        List<Long> data = patientService.deleteConsultation( consultationtId,patientId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping("/{patientId}/listConsultationsForPatient")
    public ResponseEntity<List<ConsultationResponse>> getAllConsultationsForPatient(@PathVariable("patientId") long patientId) {
        List<ConsultationResponse> consultationResponses= patientService.getAllConsultationsForPatient(patientId);
        return  new ResponseEntity<>(consultationResponses, HttpStatus.OK);
    }

}
