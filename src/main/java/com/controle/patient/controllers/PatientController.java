package com.controle.patient.controllers;

import com.controle.patient.repositories.PatientRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import javax.validation.Valid;

import com.controle.patient.entities.Patient;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("patients")
public class PatientController {
    private PatientRepository repository;

    @GetMapping
    public String listPatient(Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam(defaultValue = "") String keyword){
        Page<Patient>  pages = repository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("patients",pages.getContent());
        model.addAttribute("pages", new int [pages.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping("formPatient")
    public String formPatient(Model model){

        model.addAttribute("patient",new Patient());
        return "form";
    }

    @GetMapping("edit")
    public String edit(Model model,Long id , int page, String keyword){
        if(id == null)
            return "patientNotFound";
        Optional<Patient> patient = repository.findById(id);
        if(patient.isEmpty())
            return "patientNotFound";

        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("patient",patient.get());
        return "form";
    }

    @GetMapping("delete")
    public String delete(Long id , int page, String keyword){
        if(id == null)
            return "patientNotFound";
        Optional<Patient> patient = repository.findById(id);
        if(patient.isEmpty())
            return "patientNotFound";
        repository.delete(patient.get());
        return "redirect:/patients?page="+page+"&keyword="+keyword;
    }



    @PostMapping("save")
    public String save(Model model,@Valid Patient patient,BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page , String keyword){
        if(bindingResult.hasErrors())
            return "form";
        repository.save(patient);
        return "redirect:/patients?page="+page+"&keyword="+keyword;
    }
}