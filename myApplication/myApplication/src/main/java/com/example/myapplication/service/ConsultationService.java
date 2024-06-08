package com.example.myapplication.service;

import com.example.myapplication.entities.Consultation;
import com.example.myapplication.repositories.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    public List<Consultation> findByDossierMedicaleId(Long dossierMedicaleId) {
        return consultationRepository.findByDossierMedicale_IdDM(dossierMedicaleId);
    }

    public void save(Consultation consultation) {
        consultationRepository.save(consultation);
    }
}
