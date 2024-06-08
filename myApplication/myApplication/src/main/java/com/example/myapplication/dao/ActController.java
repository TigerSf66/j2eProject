package com.example.myapplication.dao;

import com.example.myapplication.entities.Act;
import com.example.myapplication.repositories.ActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ActController {

    @Autowired
    private ActRepository actRepository;

    @GetMapping("/acts")
    public String getAllActs(Model model) {
        List<Act> acts = actRepository.findAll();
        model.addAttribute("acts", acts);
        return "act-list";
    }
}
