package com.example.myapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name = "TYPE",
                      length = 3,
                      discriminatorType = DiscriminatorType.STRING)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String login;
    private String motDePass;
    @Enumerated(EnumType.STRING)
    private Role role;



    public Utilisateur(String login, String motDePass) {
        this.login = login;
        this.motDePass = motDePass;
    }

    public Utilisateur(String login, String motDePass, String nom) {
        this.login = login;
        this.motDePass = motDePass;
        this.nom = nom;
    }

    public Role getRole() {
        return role;
    }

}
