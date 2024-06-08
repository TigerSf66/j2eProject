package com.example.myapplication.service;

import com.example.myapplication.entities.Docteur;
import com.example.myapplication.repositories.DocteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocteurService {

    @Autowired
    private DocteurRepository docteurRepository;

    public Docteur findById(Long id) {
        return docteurRepository.findById(id).orElse(null);
    }

    public List<Docteur> findAll() {
        return docteurRepository.findAll();
    }

    public void save(Docteur docteur) {
        docteurRepository.save(docteur);
    }

    public void deleteById(Long id) {
        docteurRepository.deleteById(id);
    }

    public void updateDoctor(Long id, Docteur doctor) {
        Docteur existingDoctor = findById(id);
        if (existingDoctor != null) {
            existingDoctor.setNom(doctor.getNom());
            existingDoctor.setSpecialite(doctor.getSpecialite());
            existingDoctor.setSalaireDeBase(doctor.getSalaireDeBase());
            existingDoctor.setDisponabilite(doctor.getDisponabilite());
            existingDoctor.setAssurance(doctor.getAssurance());
            existingDoctor.setStatusActuel(doctor.getStatusActuel());
            docteurRepository.save(existingDoctor);
        }
    }

    public boolean deleteDoctor(Long id) {
        if (docteurRepository.existsById(id)) {
            docteurRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    public List<Docteur> getAllDoctors() {
        return docteurRepository.findAll();
    }
}
