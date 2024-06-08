package com.example.myapplication.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@DiscriminatorValue("DOC")
public class Docteur extends Utilisateur {

    private String specialite;
    private Double salaireDeBase;
    private String disponabilite;
    private String assurance;
    private String statusActuel;

    @CreationTimestamp
    private LocalDate dateRecrutement;

    @OneToMany(mappedBy = "médecinTraitant")
    private Collection<DossierMedicale> dossiersTraités
            = new ArrayList<>();

    public Docteur(String login, String motDePass) {
        super(login, motDePass);
    }

    public Docteur(String login, String motDePass, String nom) {
        super(login, motDePass, nom);
    }
}
