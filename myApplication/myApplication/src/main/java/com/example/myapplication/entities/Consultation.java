package com.example.myapplication.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDate dateConsultation;

    @Enumerated(EnumType.STRING)
    private TypeConsultation typeConsultation;

    @Enumerated(EnumType.STRING)
    private Acte acte;

    private double prixDeBase;

    private int nombreDeDent;

    private double prixPatient;

    private String noteMedecin;

    @ManyToOne
    @JoinColumn(name = "dossier_medical_id")
    private DossierMedicale dossierMedicale;

    @OneToMany(mappedBy = "consultation")
    private Collection<InterventionMedecin> interventions = new ArrayList<>();
    public void setDossierMedical(DossierMedicale dossierMedical) {
        this.dossierMedicale = dossierMedical;
    }
    public enum TypeConsultation {
        CONSULTATION_GENERAL,
        SUIVI,
        URGENCE
    }

    public enum Acte {
        PREVENTION,
        CHIRURGIE,
        ORTHODONTI,
        LIBELLE,
        SOINS_DES_CARIES,
        PROTHESES_DENTAIRES,
        DIAGNOSTIC
    }
}
