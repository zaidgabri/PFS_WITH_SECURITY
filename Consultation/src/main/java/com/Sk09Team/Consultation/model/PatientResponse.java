package com.Sk09Team.Consultation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Date birthDate ;
    private  String CIN  ;
    private String postCode ;
    private String email ;
    private  String  city   ;
    private  String  address ;



}
