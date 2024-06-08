package com.example.myapplication.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Embeddable
public class Adresse {
    private String rue;
    private String ville;
    private String pays;
    private String codePostal;
}
