package com.Sk09Team.Consultation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultationResponse {
    private long patientId  ;
    private long doctorId;
    private String doctorName ;
    private String specialty ;
    private String location ;
    private String startAt;
    private String  motif;
    private String place ;
    private String  medical_info ;

}
