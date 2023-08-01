package com.Sk09Team.Doctor.repository;

import com.Sk09Team.Doctor.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String > {
    List<Patient> findByDoctorId(long doctorId);
}
