package com.Sk09Team.Patient.external.client;

import com.Sk09Team.Patient.model.ConsultationRequest;
import com.Sk09Team.Patient.model.ConsultationResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CircuitBreaker(name="external",fallbackMethod = "fallback")
@FeignClient(name="CONSULTATION-SERVICE/consultation")
public interface ConsultationClient {
    @PostMapping("/reserve")
   ResponseEntity<Long> reserveConsultation(@RequestBody ConsultationRequest consultationRequest);
    default void fallback(Exception e){
        throw new RuntimeException("Consultation Service is not available!");

    }
    @DeleteMapping("/{consultationId}/{patientId}/delete")
    public ResponseEntity<List<Long>>deleteConsultationForPatient(@PathVariable("consultationId") long  consultationtId , @PathVariable("patientId") long  patientId);
    @GetMapping("/{patientId}/listConsultationsForPatient")
    public ResponseEntity<List<ConsultationResponse>> getAllConsultationsForPatient(@PathVariable("patientId") long patientId);


}
