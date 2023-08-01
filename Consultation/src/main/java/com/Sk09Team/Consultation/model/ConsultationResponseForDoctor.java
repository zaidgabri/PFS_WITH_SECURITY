package com.Sk09Team.Consultation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor@Builder
public class ConsultationResponseForDoctor {
    @JsonIgnore
    private long patientId  ;
    @JsonIgnore
    private long doctorId;
    @JsonIgnore
    private String doctorName ;
    @JsonIgnore
    private String specialty ;
    private String patientFirstName;
    private String patientLastName ;
    private String patientGender ;
    private String  patientPhone  ;
    private Date patientbirthDate ;
    private  String patientCIN  ;
    private String patientPostCode ;
    private String patientEmail ;
    private  String  patientCity   ;
    private  String  patientAddress ;
    private String location ;
    private String startAt;
    private String  motif;
    private String place ;
    private String  medical_info ;


}
