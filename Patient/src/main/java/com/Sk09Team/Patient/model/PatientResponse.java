package com.Sk09Team.Patient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponse {
    private String firstName;
    private String lastName ;
    private String gender ;
    private String  phone  ;
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private Date birthDate ;
    private  String CIN  ;
    private String postCode ;
    private String email ;
    private  String  city   ;
    private  String  address ;



}
