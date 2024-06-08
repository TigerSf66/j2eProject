package com.example.myapplication.web;

import com.example.myapplication.entities.Patient;
import com.example.myapplication.service.ServicePatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {

    ServicePatient servicePatient;

    @Autowired
    public ServiceController(ServicePatient servicePatient) {
        this.servicePatient = servicePatient;
    }

    @RequestMapping("/patients")
    public List<Patient>  getAllPatients(){
        return servicePatient.mesPatients();
    }
}
