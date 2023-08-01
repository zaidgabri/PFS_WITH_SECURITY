package com.Sk09Team.Doctor.external.client;

import com.Sk09Team.Doctor.model.ConsultationResponse;
import com.Sk09Team.Doctor.model.ConsultationResponseForDoctor;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@CircuitBreaker(name="external",fallbackMethod = "fallback")
@FeignClient(name="CONSULTATION-SERVICE/consultation")
public interface CosnultationClient {
    @PutMapping("/{consultationId}/{doctorId}/approve")
     ResponseEntity<Long> approveConsultation(@PathVariable("consultationId") long consultationId, @PathVariable("doctorId") long doctorId );
    @PutMapping("/declineConsultation/{consultationId}/{doctorId}")
     ResponseEntity<Long> declineConsultation(@PathVariable("consultationId") long consultationId,@PathVariable("doctorId") long doctorId );

    @GetMapping("/{doctorId}/listConsultationsForDoctor")
     ResponseEntity<List<ConsultationResponseForDoctor>> getAllConsultationsForDoctor(@PathVariable("doctorId") long doctorId);

    default void fallback(Exception e){
        throw new RuntimeException("Consultation Service is not available!");

    }
}
