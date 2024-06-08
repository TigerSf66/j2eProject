package com.example.myapplication.dao;

import com.example.myapplication.repositories.SituationFinanciereRepository;

public class SituationFinanciereController {

    private final SituationFinanciereRepository situationFinanciereRepository;

    public SituationFinanciereController(SituationFinanciereRepository situationFinanciereRepository) {
        this.situationFinanciereRepository = situationFinanciereRepository;
    }
}
