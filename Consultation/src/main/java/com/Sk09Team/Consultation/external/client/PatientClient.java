package com.Sk09Team.Consultation.external.client;

import com.Sk09Team.Consultation.model.PatientResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@CircuitBreaker(name="external",fallbackMethod = "fallback")
@FeignClient(name = "PATIENT-SERVICE/patient")
public interface PatientClient {
    @GetMapping("/{patientId}")
     PatientResponse getPatientByPatientId(@PathVariable long patientId);
    default void fallback(Exception e){
        throw new RuntimeException("Patient Service is not available!");

    }
}
