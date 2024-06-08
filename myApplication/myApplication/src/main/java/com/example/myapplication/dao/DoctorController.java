package com.example.myapplication.dao;

import com.example.myapplication.entities.Docteur;
import com.example.myapplication.repositories.DocteurRepository;
import com.example.myapplication.service.DocteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DoctorController {

    @Autowired
    private DocteurService doctorService;

    @Autowired
    private DocteurRepository docteurRepository;

    @GetMapping("/doctors")
    public String listDoctors(Model model) {
        model.addAttribute("doctors", docteurRepository.findAll());
        return "doctor-list"; // Thymeleaf template name
    }

    @PostMapping("/add-doctor")
    @ResponseBody
    public String addDoctor(@RequestBody Docteur doctor) {
        try {
            docteurRepository.save(doctor);
            return "Doctor added successfully.";
        } catch (Exception e) {
            return "Error adding doctor: " + e.getMessage();
        }
    }

    @GetMapping("/edit-doctor/{id}")
    public String editDoctor(@PathVariable("id") Long id, Model model) {
        Docteur doctor = doctorService.findById(id);
        if (doctor != null) {
            model.addAttribute("doctor", doctor);
            return "edit-doctor";
        } else {
            return "redirect:/doctors"; // Redirect to doctor list if doctor not found
        }
    }

    @PostMapping("/edit-doctor/{id}")
    public String updateDoctor(@PathVariable("id") Long id, @ModelAttribute("doctor") Docteur updatedDoctor) {
        Docteur doctor = doctorService.findById(id);
        if (doctor != null) {
            doctor.setNom(updatedDoctor.getNom());
            doctor.setSpecialite(updatedDoctor.getSpecialite());
            doctor.setSalaireDeBase(updatedDoctor.getSalaireDeBase());
            doctor.setDisponabilite(updatedDoctor.getDisponabilite());
            doctor.setAssurance(updatedDoctor.getAssurance());
            doctor.setStatusActuel(updatedDoctor.getStatusActuel());
            doctor.setDateRecrutement(updatedDoctor.getDateRecrutement());
            doctorService.save(doctor);
        }
        return "redirect:/doctors"; // Redirect to doctor list after update
    }

    @PostMapping("/delete-doctor/{id}")
    public String deleteDoctor(@PathVariable("id") Long id) {
        try {
            docteurRepository.deleteById(id);
            return "redirect:/doctors"; // Redirect to doctor list after delete
        } catch (Exception e) {
            return "Error deleting doctor: " + e.getMessage();
        }
    }
}
