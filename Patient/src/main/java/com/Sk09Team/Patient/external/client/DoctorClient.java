package com.Sk09Team.Patient.external.client;

import com.Sk09Team.Patient.external.response.DoctorResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@CircuitBreaker(name="external",fallbackMethod = "fallback")
@FeignClient(name="DOCTOR-SERVICE/DoctorPatientConsultation")
public interface DoctorClient {
    @GetMapping("/{city}/{specialty}/listDoctorsByCityAndSpecialty")
    public ResponseEntity<List<DoctorResponse>> getDoctorsByCityAndSpecialty(@PathVariable("city") String city, @PathVariable("specialty") String specialty) ;

    @GetMapping("/{city}/listDoctorsByCity")
    public ResponseEntity<List<DoctorResponse>> getDoctorsByCity(@PathVariable("city") String city) ;

    @GetMapping("/{specialty}/listDoctorsBySpecialty")
    public ResponseEntity<List<DoctorResponse>> getDoctorsBySpecialty( @PathVariable("specialty") String specialty) ;

    @GetMapping("/listDoctors")
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() ;
    @GetMapping("/{lastName}/listDoctorsByLastName")
    public ResponseEntity<List<DoctorResponse>> getDoctorsByLastName(@PathVariable("lastName") String lastName);
    @GetMapping("/{doctorId}/getDoctorById")
    public DoctorResponse getDoctorByDoctorId(@PathVariable long doctorId) ;



}
