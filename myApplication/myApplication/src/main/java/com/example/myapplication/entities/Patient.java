package com.example.myapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@DiscriminatorValue("PAT")
public class Patient extends Utilisateur {

    private String sexe;
    private String cin;
    private String assurance;
    private String email;

    @Embedded
    private Adresse adresse;

    @CreationTimestamp
    private LocalDate dateDAjout;

    @OneToOne(mappedBy = "patient",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private DossierMedicale dossierMedicale; // proxy

    @ManyToMany(mappedBy = "patientsARisque",
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE})
    private Collection<Risque> risques
            = new ArrayList<>();


    public void ajouterRisque(Risque risque){
        risques.add(risque);
        risque.getPatientsARisque().add(this);
    }
    public void supprimerRisque(Risque risque){
        risques.remove(risque);
        risque.getPatientsARisque().remove(this);
    }



    public void lierDossierMed(DossierMedicale dossier){
        this.dossierMedicale = dossier;
        dossier.setPatient(this);
    }

    public void détâcherDossierMed(DossierMedicale dossier){
        this.dossierMedicale = null;
        dossier.setPatient(null);
    }



    public Patient(String login, String motDePass) {
        super(login, motDePass);
    }

    public Patient(String login, String motDePass, String nom) {
        super(login, motDePass, nom);
    }


}
