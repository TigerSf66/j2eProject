package com.example.myapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Act {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAct;
    private String nom;
    private Double prixDeBase;
    private String catégorie;

    @OneToMany(mappedBy = "act")
    private Collection<InterventionMedecin> interventions =  new ArrayList<>();
}
