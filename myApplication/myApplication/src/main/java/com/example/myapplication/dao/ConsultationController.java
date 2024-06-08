package com.example.myapplication.dao;

import com.example.myapplication.entities.Consultation;
import com.example.myapplication.entities.DossierMedicale;
import com.example.myapplication.repositories.ConsultationRepository;
import com.example.myapplication.repositories.DossierMedicaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private DossierMedicaleRepository dossierMedicaleRepository;

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("consultation", new Consultation());
        return "dossier-medical";
    }

    @PostMapping("/save")
    public String saveConsultation(@ModelAttribute("consultation") Consultation consultation) {
        consultationRepository.save(consultation);
        return "redirect:/consultations";
    }

    @GetMapping
    public String listConsultations(Model model) {
        model.addAttribute("consultations", consultationRepository.findAll());
        return "consultations";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Consultation consultation = consultationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid consultation Id:" + id));
        model.addAttribute("consultation", consultation);
        return "dossier-medical";
    }

    @PostMapping("/{id}/update")
    public String updateConsultation(@PathVariable("id") Long id, @ModelAttribute("consultation") Consultation consultation) {
        consultation.setId(id);
        consultationRepository.save(consultation);
        return "redirect:/consultations";
    }

    @GetMapping("/{id}/delete")
    public String deleteConsultation(@PathVariable("id") Long id) {
        Consultation consultation = consultationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid consultation Id:" + id));
        consultationRepository.delete(consultation);
        return "redirect:/consultations";
    }
}
