package com.example.myapplication.repositories;

import com.example.myapplication.entities.Docteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocteurRepository extends JpaRepository<Docteur, Long> {
}
