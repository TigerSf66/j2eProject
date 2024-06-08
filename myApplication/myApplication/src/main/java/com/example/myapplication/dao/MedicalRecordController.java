package com.example.myapplication.dao;

import com.example.myapplication.entities.Consultation;
import com.example.myapplication.entities.DossierMedicale;
import com.example.myapplication.entities.Patient;
import com.example.myapplication.entities.SituationFinanciere;
import com.example.myapplication.repositories.ConsultationRepository;
import com.example.myapplication.service.ConsultationService;
import com.example.myapplication.service.DossierMedicaleService;
import com.example.myapplication.service.ServicePatient;
import com.example.myapplication.service.SituationFinanciereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MedicalRecordController{

    @Autowired
    private DossierMedicaleService dossierMedicaleService;

    @Autowired
    private ServicePatient patientService;

    @Autowired
    private ConsultationService consultationService;


    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private SituationFinanciereService situationFinanciereService;





    @GetMapping("/medicalRecord/view/{id}")
    public String viewMedicalRecord(@PathVariable("id") Long id, Model model) {
        DossierMedicale dossierMedicale = dossierMedicaleService.findById(id);
        if (dossierMedicale == null) {
            return "error/404";
        }

        Patient patient = patientService.findById(dossierMedicale.getPatientId());
        List<Consultation> consultations = consultationRepository.findByDossierMedicale_IdDM(id);
        List<SituationFinanciere> financialSituations = situationFinanciereService.findByDossierMedicale_IdDM(id);

        // Add the medical record and patient details to the model
        model.addAttribute("dossierMedicale", dossierMedicale);
        model.addAttribute("patient", patient);
        model.addAttribute("consultations", consultations);
        model.addAttribute("financialSituations", financialSituations);
        model.addAttribute("newConsultation", new Consultation());
        model.addAttribute("newFinancialSituation", new SituationFinanciere());
        return "medicalRecord/view"; // Ensure this matches your Thymeleaf template name
    }

    @DeleteMapping("/medicalRecord/{id}/deleteFinancialSituation")
    public ResponseEntity<Void> deleteFinancialSituation(@PathVariable Long id) {
        situationFinanciereService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/medicalRecord/{dossierId}/facture/{situationId}/generate")
    public String generateFacture(@PathVariable("dossierId") Long dossierId, @PathVariable("situationId") Long situationId, Model model) {
        // Logic to generate the facture
        SituationFinanciere situation = situationFinanciereService.findById(situationId).orElse(null);
        if (situation == null) {
            return "error/404"; // Handle situation not found
        }

        // Pass the situation to the view
        model.addAttribute("situation", situation);

        return "medicalRecord/facture";
    }


    @PostMapping("/medicalRecord/{id}/addConsultation")
    public String addConsultation(@PathVariable("id") Long id, @ModelAttribute Consultation consultation) {
        DossierMedicale dossierMedicale = dossierMedicaleService.findById(id);
        if (dossierMedicale == null) {
            // Handle the case where the medical record is not found
            return "error/404"; // Or another error page
        }
        consultation.setDossierMedical(dossierMedicale);
        consultation.setId(null);
        consultationRepository.save(consultation);
        return "redirect:/medicalRecord/view/" + id;
    }
    @PostMapping("/medicalRecord/{id}/addFinancialSituation")
    public String addFinancialSituation(@PathVariable("id") Long id, @ModelAttribute SituationFinanciere situationFinanciere, Model model) {


        DossierMedicale dossierMedicale = dossierMedicaleService.findById(id);
        if (dossierMedicale == null) {
            return "error/404";
        }
        situationFinanciere.setId(null);
        situationFinanciere.setDossierMedicale(dossierMedicale);
        situationFinanciereService.save(situationFinanciere);
        return viewMedicalRecord(id, model);
    }

}
