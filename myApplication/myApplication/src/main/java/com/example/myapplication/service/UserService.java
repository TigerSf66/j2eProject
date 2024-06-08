package com.example.myapplication.service;

import com.example.myapplication.entities.Role;
import com.example.myapplication.entities.Utilisateur;
import com.example.myapplication.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UtilisateurRepository utilisateurRepository;

    public UserService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Optional<Utilisateur> authenticate(String login, String motDePass) {
        return utilisateurRepository.findByLoginAndMotDePass(login, motDePass)
                .filter(utilisateur -> utilisateur.getRole() == Role.ROLE_SECRETARY);
    }
}
