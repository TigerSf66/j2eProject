package com.example.myapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DossierMedicale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDM;
    @CreationTimestamp
    private LocalDate dateCréation;

    @OneToOne
    @JoinColumn(name = "id_Patient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_Docteur")
    private Docteur médecinTraitant;

    @OneToMany(mappedBy = "dossierMedicale",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Collection<Consultation> consultations = new ArrayList<>();

    // New attributes
    private String situationFinanciere;
    private String numeroDossier;
    private String statusPaiement;

    public Long getPatientId() {
        return patient.getId();
    }
}
