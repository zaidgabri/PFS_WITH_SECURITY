package com.Sk09Team.Doctor.model;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorRequest {
    private String firstName ;
    private String lastName ;
    private String email ;
    private String city ;
    private  String specialty ;
//    private String postCode;
//    private  String address  ;
    private String phoneNumber ;
   private String password ;



}
