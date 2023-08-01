package com.Sk09Team.Consultation.service;

import com.Sk09Team.Consultation.entity.Consultation;
import com.Sk09Team.Consultation.external.client.response.DoctorResponse;
import com.Sk09Team.Consultation.model.ConsultationRequest;
import com.Sk09Team.Consultation.model.ConsultationResponse;
import com.Sk09Team.Consultation.model.ConsultationResponseForDoctor;

import java.util.List;

public interface ConsultationService {


    long reserveConsultation(ConsultationRequest consultationRequest);

    List<Long> deleteConsultation(long consultationtId, long patientId);

   long  approveConsultation(long consultationId, long doctorId);
    long  declineConsultation(long consultationId, long doctorId);

    List<ConsultationResponseForDoctor> getAllConsultationsForDoctor(long doctorId);
    List<ConsultationResponse> getAllConsultationsForPatient(long patientId);
}
