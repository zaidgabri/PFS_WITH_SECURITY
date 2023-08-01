package com.Sk09Team.Doctor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;



import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder



public class DoctorFullProfileRequest {
    private String firstName;
    private String lastName;
    private String password;
    private String registrationNumber;
    private String address;
    private String email;
    private String phone;
    private String city;
    private String postCode;
    private String specialty;
    private String location;
    private List<String> diplomas;
    private String place;
    private PaymentMode paymentMode;
    private List<String> languages;
    private String calendar;


}
