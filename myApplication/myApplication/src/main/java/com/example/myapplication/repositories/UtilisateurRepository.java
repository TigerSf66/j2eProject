package com.example.myapplication.repositories;

import com.example.myapplication.entities.Role;
import com.example.myapplication.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByLoginAndMotDePass(String login, String motDePass);
}
