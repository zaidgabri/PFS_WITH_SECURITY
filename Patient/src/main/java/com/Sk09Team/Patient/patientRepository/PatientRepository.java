package com.Sk09Team.Patient.patientRepository;

import com.Sk09Team.Patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long > {

    Optional<Patient> findByEmail(String email);

    boolean existsByEmail(String email);
}
