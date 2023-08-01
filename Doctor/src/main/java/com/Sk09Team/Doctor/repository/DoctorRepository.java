package com.Sk09Team.Doctor.repository;

import com.Sk09Team.Doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    List<Doctor> findByCity(String city);
    List<Doctor> findBySpecialty(String specialty); // Update the method name to match the field name
    List<Doctor> findByCityAndSpecialty(String city, String specialty);
    List<Doctor> findByLastName(String lastName);

    boolean existsByEmail(String email);



    Optional<Doctor> findByEmail(String email);


}
