package com.example.myapplication.repositories;

import com.example.myapplication.entities.DossierMedicale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DossierMedicaleRepository extends JpaRepository<DossierMedicale, Long> {
}
