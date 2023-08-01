package com.Sk09Team.Consultation.controller;
import com.Sk09Team.Consultation.model.ConsultationRequest;
import com.Sk09Team.Consultation.model.ConsultationResponse;
import com.Sk09Team.Consultation.model.ConsultationResponseForDoctor;
import com.Sk09Team.Consultation.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/consultation")
public class ConsultationController {
    @Autowired
    ConsultationService consultationService;
    @PostMapping("/reserve")
    public ResponseEntity<Long> reserveConsultation(@RequestBody ConsultationRequest consultationRequest){
        long consultationId  =consultationService.reserveConsultation(consultationRequest);
        return new ResponseEntity<>( consultationId, HttpStatus.CREATED);
    }
    @DeleteMapping("/{consultationId}/{patientId}/delete")
    public ResponseEntity<List<Long>>deleteConsultationForPatient(@PathVariable("consultationId") long  consultationtId ,@PathVariable("patientId") long  patientId){
        List<Long> data = consultationService.deleteConsultation( consultationtId,patientId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @PutMapping("/{consultationId}/{doctorId}/approve")
    public ResponseEntity<Long> approveConsultation(@PathVariable("consultationId") long consultationId,@PathVariable("doctorId") long doctorId ){
        long consId = consultationService.approveConsultation(consultationId,doctorId);
        return new ResponseEntity<>(consId,HttpStatus.OK);
    }
    @PutMapping("/{consultationId}/{doctorId}/decline")
    public ResponseEntity<Long> declineConsultation(@PathVariable("consultationId") long consultationId,@PathVariable("doctorId") long doctorId ){
        long consId = consultationService.approveConsultation(consultationId,doctorId);
        return new ResponseEntity<>(consId,HttpStatus.OK);
    }
    @GetMapping("/{doctorId}/listConsultationsForDoctor")
    public ResponseEntity<List<ConsultationResponseForDoctor>> getAllConsultationsForDoctor(@PathVariable("doctorId") long doctorId) {
        List<ConsultationResponseForDoctor> consultationResponses= consultationService.getAllConsultationsForDoctor(doctorId);
        return  new ResponseEntity<>(consultationResponses, HttpStatus.OK);
    }
    @GetMapping("/{patientId}/listConsultationsForPatient")
    public ResponseEntity<List<ConsultationResponse>> getAllConsultationsForPatient(@PathVariable("patientId") long patientId) {
        List<ConsultationResponse> consultationResponses= consultationService.getAllConsultationsForPatient(patientId);
        return  new ResponseEntity<>(consultationResponses, HttpStatus.OK);
    }
}







































































































