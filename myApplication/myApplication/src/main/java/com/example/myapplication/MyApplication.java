package com.example.myapplication;

import com.example.myapplication.entities.Adresse;
import com.example.myapplication.entities.Patient;
import com.example.myapplication.repositories.PatientRepository;
import com.example.myapplication.service.ServicePatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication implements CommandLineRunner {

    @Autowired
    ServicePatient servicePatient;

    @Autowired
    PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {

//        var p1 = new Patient("soufiane", "1234", "otmane");
//        p1.setAdresse(new Adresse("Salam", "Sale", "Maroc", "11000"));
//        var p2 = new Patient("amine", "4567", "Adli");
//        p2.setAdresse(new Adresse("Babel", "Rabat", "Maroc", "11000"));
//        var p3 = new Patient("soufiane", "5431", "halim");
//        p3.setAdresse(new Adresse("Agdal", "Rabat", "Maroc", "11000"));
//        var p4 = new Patient("Mohamed", "654323", "Khelifi");
//        p4.setAdresse(new Adresse("Babel", "Ouazane", "Maroc", "11030"));
//
//        servicePatient.ajouterDesPatient(p1,p2,p3,p4);
        servicePatient.mesPatients();

    }
}
