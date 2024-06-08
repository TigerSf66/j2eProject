package com.example.myapplication.service;


import com.example.myapplication.entities.DossierMedicale;
import com.example.myapplication.entities.Patient;
import com.example.myapplication.repositories.DossierMedicaleRepository;
import com.example.myapplication.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.Arrays;
import java.util.List;

@Service
public class ServicePatient {

    PatientRepository dao;

    @Autowired
    private DossierMedicaleRepository dossierMedicaleRepository;

    @Autowired
    public ServicePatient(PatientRepository dao) {
        this.dao = dao;
    }

    public Patient findById(Long id) {
        return dao.findById(id).orElse(null);
    }
    public void createPatientWithDossier(Patient patient) {
        dao.save(patient);
        DossierMedicale dossierMedicale = new DossierMedicale();
        dossierMedicale.setPatient(patient);
        dossierMedicaleRepository.save(dossierMedicale);
        patient.setDossierMedicale(dossierMedicale);
        dao.save(patient);
    }


    public void ajouterDesPatient(Patient... patients){
        dao.saveAll(Arrays.asList(patients));
    }
    public List<Patient> mesPatients() {
        return dao.findAll();
    }
}
