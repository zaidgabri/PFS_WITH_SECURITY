package com.Sk09Team.Patient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultationRequest {
    private long patientId  ;
    private long doctorId;
    private String doctorName ;
    private String specialty ;
    private String location ;
    private String startAt;
    private String  motif;
    private String place ;
    private String  medical_info ;
    private ConsultationStatus consultationStatus = ConsultationStatus.PENDING;

}
