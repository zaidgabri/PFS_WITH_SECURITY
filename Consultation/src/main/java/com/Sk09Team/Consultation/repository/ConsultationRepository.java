package com.Sk09Team.Consultation.repository;

import com.Sk09Team.Consultation.entity.Consultation;
import com.Sk09Team.Consultation.model.ConsultationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Consultation c WHERE c.id = :consultationId AND c.patientId = :patientId")
    void deleteByConsultationIdAndPatientId(@Param("consultationId") Long consultationId, @Param("patientId") Long patientId);

    Optional<Consultation> findByIdAndDoctorId(Long id, Long doctorId);
    List<Consultation> findByDoctorId(Long doctorId);
    List<Consultation> findByPatientId(Long patientId);


}
