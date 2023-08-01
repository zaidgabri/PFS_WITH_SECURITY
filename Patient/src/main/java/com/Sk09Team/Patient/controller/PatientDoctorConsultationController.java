package com.Sk09Team.Patient.controller;

import com.Sk09Team.Patient.external.response.DoctorResponse;
import com.Sk09Team.Patient.model.PatientRequest;
import com.Sk09Team.Patient.model.PatientResponse;
import com.Sk09Team.Patient.model.UpdateProfileResponse;
import com.Sk09Team.Patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")

@RequestMapping("/PatientDoctorConsultation")
public class PatientDoctorConsultationController {
    @Autowired
    PatientService patientService;




    @PutMapping("/profile/updateProfile/{patientId}")
    public ResponseEntity<UpdateProfileResponse>updateProfile(@PathVariable("patientId")  long patientId , @RequestBody PatientRequest patientRequest){

        return new ResponseEntity<>(UpdateProfileResponse.builder().message(patientService.updateProfile(patientId,patientRequest)).build(), HttpStatus.OK);
    }








    @GetMapping("/{city}/{specialty}/listDoctorsByCityAndSpecialty")
    public ResponseEntity<List<DoctorResponse>> getDoctorsByCityAndSpecialty(@PathVariable("city") String city, @PathVariable("specialty") String specialty) {
        return  patientService.getDoctorsByCityAndSpecialty(city,specialty);
    }




    @GetMapping("/{lastName}/listDoctorsByLastName")
    public ResponseEntity<List<DoctorResponse>> getDoctorsByCityAndSpecialty(@PathVariable("lastName") String lastName) {
        return  patientService.getDoctorsByLastName(lastName);
    }



    @GetMapping("/{city}/listDoctorsByCity")
    public ResponseEntity<List<DoctorResponse>> getDoctorsByCity(@PathVariable("city") String city) {
        return  patientService.getDoctorsByCity(city);
    }
        @GetMapping("/{specialty}/listDoctorsBySpecialty")
    public ResponseEntity<List<DoctorResponse>> getDoctorsBySpecialty( @PathVariable("specialty") String specialty) {
        return  patientService.getDoctorsBySpecialty(specialty);
    }
    @GetMapping("/listDoctors")
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {
        return  patientService.getAllDoctors();
    }

    @GetMapping("/{patientId}/getPatientById")
    public PatientResponse getPatientByPatientId(@PathVariable long patientId) {
        return patientService.getPatientByPatientId(patientId);
    }

}
