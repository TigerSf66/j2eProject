package com.example.myapplication.repositories;

import com.example.myapplication.entities.Act;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActRepository extends JpaRepository<Act, Long> {
}
