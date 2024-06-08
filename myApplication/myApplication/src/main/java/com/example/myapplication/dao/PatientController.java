package com.example.myapplication.dao;

import com.example.myapplication.entities.Consultation;
import com.example.myapplication.entities.DossierMedicale;
import com.example.myapplication.entities.Patient;
import com.example.myapplication.repositories.PatientRepository;
import com.example.myapplication.service.ServicePatient;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ServicePatient patientService;


    @GetMapping("/patients")
    public String getAllPatients(Model model) {
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        return "patient-list";
    }
    // Controller method for handling edit request
    @GetMapping("/edit/{id}")
    public String editPatient(@PathVariable Long id, Model model) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        patientOptional.ifPresent(patient -> model.addAttribute("patient", patient));
        return "edit-patient";
    }

    // Controller method for handling update request
    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable Long id, Patient updatedPatient) {
        patientRepository.findById(id)
                .ifPresent(patient -> {
                    patient.setNom(updatedPatient.getNom());
                    patient.setLogin(updatedPatient.getLogin());
                    patient.setMotDePass(updatedPatient.getMotDePass());
                    patient.setRole(updatedPatient.getRole());
                    patient.setAdresse(updatedPatient.getAdresse());
                    patient.setDateDAjout(updatedPatient.getDateDAjout());
                    patientRepository.save(patient);
                });
        return "redirect:/patients"; // Redirect to the patient list after update
    }
    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientRepository.deleteById(id);
        return "redirect:/patients"; // Redirect to the patient list after delete
    }
    // Controller method for handling search request
    @GetMapping("/search")
    public String searchPatients(@RequestParam String keyword, Model model) {
        List<Patient> patients = patientRepository.findByNomContainingIgnoreCase(keyword);
        model.addAttribute("patients", patients);
        return "patient-list";
    }
    @PostMapping("/patients/add")
    public String addPatient(@ModelAttribute Patient patient) {
        patientRepository.save(patient);
        DossierMedicale dossierMedicale = new DossierMedicale();
        dossierMedicale.setDateCréation(LocalDate.now());
        dossierMedicale.setPatient(patient);
        patient.setDossierMedicale(dossierMedicale);
        patientRepository.save(patient);
        return "redirect:/patients"; // Redirect to the patient list after adding
    }
    @GetMapping("/medicalRecord/{id}")
    public String viewMedicalRecord(@PathVariable Long id, Model model) {
        // Retrieve the patient from the database based on the id
        Patient patient = patientService.findById(id);
        model.addAttribute("patient", patient);
        return "medical_record";
    }

    @GetMapping("/patient/dossier/{id}")
    public String showDossierMedical(@PathVariable Long id, Model model) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            DossierMedicale dossierMedicale = patient.getDossierMedicale();
            if (dossierMedicale == null) {
                // Initialize a new DossierMedicale object if it's null
                dossierMedicale = new DossierMedicale();
                dossierMedicale.setDateCréation(LocalDate.now()); // Set the current date as an example
            }
            model.addAttribute("patient", patient);
            model.addAttribute("dossierMedicale", dossierMedicale);
            model.addAttribute("consultation", new Consultation());
            return "dossier-medical";
        } else {
            // Handle the case where the patient is not found
            return "error"; // Or redirect to another page
        }
    }




}
