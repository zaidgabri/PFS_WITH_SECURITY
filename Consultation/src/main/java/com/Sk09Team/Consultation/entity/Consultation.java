package com.Sk09Team.Consultation.entity;

import com.Sk09Team.Consultation.model.ConsultationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consultation {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;
    @Column(name = "patientId")
    private long patientId ;
    @Column(name = "doctorId")
    private long doctorId ;
    @Column(name = "doctorName")
    private String doctorName ;
    @Column(name = "speciality")
    private String specialty ;
    @Column(name = "location")
    private String location ;
    @Column(name = "startAt")
    private String startAt;
    @Column(name = "motif ")
    private String  motif;
    @Column(name = "place")
    private String place ;
    @Column(name = "medical_info")
    private String  medical_info ;
    @Column(name="status")
    private ConsultationStatus consultationStatus;




}
