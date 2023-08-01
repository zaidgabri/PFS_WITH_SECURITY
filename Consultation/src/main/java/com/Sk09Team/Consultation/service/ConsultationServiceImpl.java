package com.Sk09Team.Consultation.service;

import com.Sk09Team.Consultation.entity.Consultation;
import com.Sk09Team.Consultation.external.client.DoctorClient;
import com.Sk09Team.Consultation.external.client.PatientClient;
import com.Sk09Team.Consultation.model.*;
import com.Sk09Team.Consultation.repository.ConsultationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultationServiceImpl implements ConsultationService {
    @Autowired
    private PatientClient patientClient;
    @Autowired
     private ConsultationRepository consultationRepository;
   // @Autowired
    private  DoctorClient doctorClient;

    @Override
    public long reserveConsultation(ConsultationRequest consultationRequest) {
        Consultation consultation = Consultation.builder()
                .doctorName(consultationRequest.getDoctorName())
                .patientId(consultationRequest.getPatientId())
                .motif(consultationRequest.getMotif())
                .place(consultationRequest.getPlace())
                .specialty(consultationRequest.getSpecialty())
                .startAt(consultationRequest.getStartAt())
                .doctorId(consultationRequest.getDoctorId())
                .medical_info(consultationRequest.getMedical_info())
                .location(consultationRequest.getLocation())
                .consultationStatus(consultationRequest.getConsultationStatus())
                .build();
               consultationRepository.save(consultation);
               return consultation.getId();

    }

    @Override
    public List<Long> deleteConsultation(long consultationtId, long patientId) {
        List<Long> consultationSpecs = new ArrayList<>();
        consultationSpecs.add(consultationtId);
        consultationSpecs.add(patientId);
        consultationRepository.deleteByConsultationIdAndPatientId( consultationtId,patientId);
        return consultationSpecs;
    }
    @Override
    public long approveConsultation(long consultationId, long doctorId){
        Consultation consultation = consultationRepository.findByIdAndDoctorId(consultationId, doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Consultation not found with id " + consultationId + " and doctor id " + doctorId));
        consultation.setConsultationStatus(ConsultationStatus.APPROVED);
        consultationRepository.save(consultation);
        return consultation.getId();
    }
    @Override
    public long declineConsultation(long consultationId, long doctorId) {      Consultation consultation = consultationRepository.findByIdAndDoctorId(consultationId, doctorId)
            .orElseThrow(() -> new EntityNotFoundException("Consultation not found with id " + consultationId + " and doctor id " + doctorId));
        consultation.setConsultationStatus(ConsultationStatus.DECLINED);
        consultationRepository.save(consultation);
        return consultation.getId();
    }

    @Override
    public List<ConsultationResponseForDoctor> getAllConsultationsForDoctor(long doctorId) {
        List<Consultation> consultations = consultationRepository.findByDoctorId(doctorId);
        List<ConsultationResponseForDoctor> consultationResponseForDoctors=new ArrayList<>();
        for(Consultation consultation :consultations){
        PatientResponse patientResponse=patientClient.getPatientByPatientId(consultation.getPatientId());
        ConsultationResponseForDoctor consultationResponse = ConsultationResponseForDoctor.builder()
                    .doctorName(consultation.getDoctorName())
                    .doctorId(consultation.getDoctorId())
                    .place(consultation.getPlace())
                    .motif(consultation.getMotif())
                    .startAt(consultation.getStartAt())
                    .medical_info(consultation.getMedical_info())
                    .location(consultation.getLocation())
                    .patientId(consultation.getPatientId())
                    .specialty(consultation.getSpecialty())
                    .patientFirstName(patientResponse.getFirstName())
                    .patientLastName(patientResponse.getLastName())
                    .patientGender(patientResponse.getGender())
                    .patientPhone(patientResponse.getPhone())
                    .patientbirthDate(patientResponse.getBirthDate())
                    .patientCIN(patientResponse.getCIN())
                    .patientPostCode(patientResponse.getPostCode())
                    .patientEmail(patientResponse.getEmail())
                    .patientCity(patientResponse.getCity())
                    .patientAddress(patientResponse.getAddress())
                    .build();
            consultationResponseForDoctors.add(consultationResponse);
        }
        return consultationResponseForDoctors;
    }

    @Override
    public List<ConsultationResponse> getAllConsultationsForPatient(long patientId) {
        List<Consultation> consultations = consultationRepository.findByPatientId(patientId);
        return getConsultationResponses(consultations);

    }

    private List<ConsultationResponse> getConsultationResponses(List<Consultation> consultations) {
        List<ConsultationResponse> consultationResponses = new ArrayList<>();
        for (Consultation consultation : consultations) {
            ConsultationResponse consultationResponse = ConsultationResponse.builder()
                    .doctorName(consultation.getDoctorName())
                    .doctorId(consultation.getDoctorId())
                    .place(consultation.getPlace())
                    .motif(consultation.getMotif())
                    .startAt(consultation.getStartAt())
                    .medical_info(consultation.getMedical_info())
                    .location(consultation.getLocation())
                    .patientId(consultation.getPatientId())
                    .specialty(consultation.getSpecialty())
                    .build();
            consultationResponses.add(consultationResponse);

        }
        return consultationResponses;
    }



}
