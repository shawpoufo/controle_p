package com.controle.patient.security.controller;

import javax.validation.Valid;

import com.controle.patient.security.entities.SignUpModel;
import com.controle.patient.security.services.ISecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller@Transactional
public class SecurityController {

    @Autowired
    ISecurityService serviceImpl;
    @GetMapping("signup")
    public String signUp(Model model,SignUpModel signUpModel)
    {
        model.addAttribute("passNotSame","");
        return "signup";
    }

    @PostMapping("signup")
    public String save(Model model,@Valid SignUpModel signUpModel,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) 
        {
            // model.addAttribute("model", signUpModel);
            return "signup";
        }
        else if(!signUpModel.getPassword().equals(signUpModel.getRePassword()))
        {
            model.addAttribute("passNotSame","re-password et password ne sont pas les même !");
            return "signup";
        }
        else
        {
            serviceImpl.saveNewUser(signUpModel.getUsername(), signUpModel.getPassword(), signUpModel.getPassword());
            serviceImpl.addRoleToUser(signUpModel.getUsername(), "USER");
            return "redirect:/login";
        }
    }
}
