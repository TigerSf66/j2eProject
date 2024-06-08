package com.example.myapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InterventionMedecin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIM;

    @ManyToOne
    private Act act;

    @ManyToOne
    private Consultation consultation;
}
