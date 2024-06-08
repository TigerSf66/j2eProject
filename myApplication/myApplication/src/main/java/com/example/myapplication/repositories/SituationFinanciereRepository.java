package com.example.myapplication.repositories;

import com.example.myapplication.entities.SituationFinanciere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SituationFinanciereRepository extends JpaRepository<SituationFinanciere, Long> {
    List<SituationFinanciere> findByDossierMedicale_IdDM(Long id);
    void deleteById(Long id);

}
