package com.Sk09Team.Doctor.controller;

import com.Sk09Team.Doctor.entity.Patient;
import com.Sk09Team.Doctor.model.ConsultationResponseForDoctor;
import com.Sk09Team.Doctor.repository.MotifRepository;
import com.Sk09Team.Doctor.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor

@CrossOrigin(origins = "http://localhost:5173")

public class DoctorController {

//    private final AuthenticationService service;

    @Autowired
    DoctorService doctorService;
    @Autowired
    MotifRepository motifRepository;

//    @PreAuthorize("hasAuthority('doctor:update')")
    @PutMapping("/{consultationId}/{doctorId}/approve")
    public ResponseEntity<Long> approveConsultation(@PathVariable("consultationId") Long consultationId,
                                                      @PathVariable("doctorId") Long doctorId) {

        return doctorService.approveConsultation(consultationId,doctorId);
    }
//    @PostMapping("/addMotif")
//    public ResponseEntity<String> addMotif(@RequestBody List<Motif> motifs){
//        for ( Motif motif: motifs
//             ) {
//            motifRepository.save(motif);
//        }
//        return new ResponseEntity<>("Added",HttpStatus.OK);
//    }
//    @PreAuthorize("hasAuthority('doctor:write')")
    @GetMapping("/{doctorId}/listConsultationsForDoctor")
    public ResponseEntity<List<ConsultationResponseForDoctor>> getAllConsultationsForDoctor(@PathVariable("doctorId") long doctorId){
        return (ResponseEntity<List<ConsultationResponseForDoctor>>) doctorService.getAllConsultationsForDoctor(doctorId);
    }
    @GetMapping("/{doctorId}/patients")
    public ResponseEntity<List<Patient>> getAllPatients(@PathVariable("doctorId") long doctorId ) {
        List<Patient> patients = doctorService.getAllPatientsByDoctorId(doctorId);
        return ResponseEntity.ok(patients);
    }
//   @PreAuthorize("hasAuthority('doctor:update')")




}
