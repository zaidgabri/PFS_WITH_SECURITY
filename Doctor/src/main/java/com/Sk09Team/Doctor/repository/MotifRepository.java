package com.Sk09Team.Doctor.repository;

import com.Sk09Team.Doctor.entity.Motif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotifRepository  extends JpaRepository<Motif, Long > {
//    List<Motif> findByDoctorId(long doctorId);
//    @Query("SELECT m.name as name FROM Motif m WHERE m.doctor.doctorId = :doctorId")
//    List<Motif> findByDoctorId(@Param("doctorId") long doctorId);
}
