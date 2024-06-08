package com.example.myapplication.repositories;

import com.example.myapplication.entities.Consultation;
import com.example.myapplication.entities.DossierMedicale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByDossierMedicale_IdDM(Long idDM);
}