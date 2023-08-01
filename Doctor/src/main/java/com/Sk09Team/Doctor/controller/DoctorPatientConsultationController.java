package com.Sk09Team.Doctor.controller;

import com.Sk09Team.Doctor.entity.Motif;
import com.Sk09Team.Doctor.model.DoctorFullProfileRequest;
import com.Sk09Team.Doctor.model.DoctorResponse;
import com.Sk09Team.Doctor.repository.MotifRepository;
import com.Sk09Team.Doctor.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DoctorPatientConsultation")

@CrossOrigin(origins = "http://localhost:5173")

@RequiredArgsConstructor
public class DoctorPatientConsultationController {
    @Autowired
    MotifRepository motifRepository;

    @Autowired
    DoctorService doctorService;



    @GetMapping("/test")
    public String consultationServiceFallback() {
        return "it works";

    }
    @PostMapping("/addMotif")
    public ResponseEntity<String> addMotif(@RequestBody List<Motif> motifs){
        for ( Motif motif: motifs
        ) {
            motifRepository.save(motif);
        }
        return new ResponseEntity<>("Added",HttpStatus.OK);
    }


//
//    @GetMapping("/getMotifs/{doctorId}")
//    public List<Motif> getMotifs(@PathVariable("doctorId") long doctorId) {
//        List<Motif> motifs
//                =  motifRepository.findByDoctorId(doctorId);
//
//        return motifs;
//    }


    @GetMapping("/{city}/{specialty}/listDoctorsByCityAndSpecialty")
    public ResponseEntity<List<DoctorResponse>> getDoctorsByCityAndSpecialty(@PathVariable("city") String city, @PathVariable("specialty") String specialty) {
        List<DoctorResponse> doctorResponse
                =  doctorService.getDoctorsByCityAndSpecialty(city, specialty);

        return new ResponseEntity<>(doctorResponse,
                HttpStatus.OK);
    }
    @PutMapping("profile/updateProfile/{doctorId}")
    public ResponseEntity<String> updateDoctor(@PathVariable long doctorId, @RequestBody DoctorFullProfileRequest doctorRequest) {
        doctorService.updateDoctor(doctorId,doctorRequest);
        return ResponseEntity.ok("Doctor updated successfully.");

    }

    //    @PreAuthorize("hasAuthority('doctor:read')")
    @GetMapping("/{city}/listDoctorsByCity")
    public ResponseEntity<List<DoctorResponse>> getDoctorsByCity(@PathVariable("city") String city) {
        List<DoctorResponse> doctorResponses
                =  doctorService.getDoctorsByCity(city);

        return new ResponseEntity<>(doctorResponses,
                HttpStatus.OK);
    }
    @GetMapping("/{specialty}/listDoctorsBySpecialty")
    public ResponseEntity<List<DoctorResponse>> getDoctorsBySpecialty( @PathVariable("specialty") String specialty) {
        List<DoctorResponse> doctorResponses
                =  doctorService.getDoctorsBySpecialty(specialty);

        return new ResponseEntity<>(doctorResponses,
                HttpStatus.OK);
    }
    @GetMapping("/{lastName}/listDoctorsByLastName")
    public ResponseEntity<List<DoctorResponse>> getDoctorsByLastName(@PathVariable("lastName") String lastName) {
        List<DoctorResponse> doctorResponses
                =  doctorService.getDoctorsByLastName(lastName);
        return new ResponseEntity<>(doctorResponses,
                HttpStatus.OK);


    }
    @GetMapping("/listDoctors")
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {
        List<DoctorResponse> doctorResponses
                =  doctorService.getAllDoctors();

        return new ResponseEntity<>(doctorResponses,
                HttpStatus.OK);
    }
    @GetMapping("/{doctorId}/getDoctorById")
    public DoctorResponse getDoctorByDoctorId(@PathVariable long doctorId) {
        return doctorService.getDoctorByDoctorId(doctorId);
    }
}
