package com.example.myapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Risque {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRisque;
    private String nom;
    private String description;
    private String catégorie;

    @ManyToMany
    private Collection<Patient> patientsARisque
            = new ArrayList<>();

    public void ajouterPatient(Patient patient){
        patientsARisque.add(patient);
        patient.getRisques().add(this);
    }
    public void supprimerPatient(Patient patient){
        patientsARisque.remove(patient);
        patient.getRisques().remove(this);
    }
}
