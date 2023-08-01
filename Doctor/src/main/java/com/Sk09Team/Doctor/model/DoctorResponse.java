package com.Sk09Team.Doctor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponse {
    private long doctorId;
    private String city;
    private String specialty;
    private String firstName;
    private String lastName;
    private String location;
    private List<String> diplomas;
    private String doctorPhone;
    private String doctorEmail;
    private String address;
    private String place;
    private PaymentMode paymentMode;
    private List<String> languages;
    private String calendar ;
}
