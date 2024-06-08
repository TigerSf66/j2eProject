package com.example.myapplication.service;

import com.example.myapplication.entities.SituationFinanciere;
import com.example.myapplication.repositories.SituationFinanciereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SituationFinanciereService {

    @Autowired
    private SituationFinanciereRepository situationFinanciereRepository;

    public SituationFinanciere save(SituationFinanciere situationFinanciere) {
        return situationFinanciereRepository.save(situationFinanciere);
    }

    // SituationFinanciereService.java
    public List<SituationFinanciere> findByDossierMedicale_IdDM(Long id) {
        return situationFinanciereRepository.findByDossierMedicale_IdDM(id);
    }

    public void deleteById(Long id) {
        situationFinanciereRepository.deleteById(id);
    }

    public Optional<SituationFinanciere> findById(Long id) {
        return situationFinanciereRepository.findById(id);
    }


}
