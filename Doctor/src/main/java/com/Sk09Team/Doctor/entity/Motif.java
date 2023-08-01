package com.Sk09Team.Doctor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Motif {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long motifId;
    private  String name;
//    @ManyToOne
//    private Doctor doctor;

//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "doctor_id")
//    @JsonIgnore
//    private Doctor doctor;


}
