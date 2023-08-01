package com.Sk09Team.Patient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor@Builder


public class PatientRequest {
    private String firstName;
    private String lastName ;
   private String gender ;
    private String  phone  ;
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date birthDate ;
    private  String cin  ;
    private String postCode ;
    private String email ;
    private  String  city   ;
    private  String  address ;
    private String password  ;

}
