package com.Sk09Team.Doctor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {
    private long doctorId ;
    @Id
    private  String patientCIN  ;

    private String patientFirstName;
    private String patientLastName ;
    private String patientGender ;
    private String  patientPhone  ;
    private Date patientbirthDate ;
    private String patientPostCode ;
    private String patientEmail ;
    private  String  patientCity   ;
    private  String  patientAddress ;


}
