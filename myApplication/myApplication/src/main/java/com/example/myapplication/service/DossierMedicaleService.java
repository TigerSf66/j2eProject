package com.example.myapplication.service;

import com.example.myapplication.entities.DossierMedicale;
import com.example.myapplication.repositories.DossierMedicaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DossierMedicaleService {


    @Autowired
    private DossierMedicaleRepository dossierMedicaleRepository;

    public DossierMedicale findById(Long id) {
        return dossierMedicaleRepository.findById(id).orElse(null);
    }
}

