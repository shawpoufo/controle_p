package com.controle.patient.security.services;

import java.util.UUID;

import com.controle.patient.security.entities.AppRole;
import com.controle.patient.security.entities.AppUser;
import com.controle.patient.security.repositories.AppRoleRepository;
import com.controle.patient.security.repositories.AppUserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;

@AllArgsConstructor @Slf4j @Transactional @Service
public class SecurityServiceImpl implements ISecurityService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository ;
    private PasswordEncoder encoder;
    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("Password not match");
        String hashedPWD = encoder.encode(password);
        AppUser newAppUser = AppUser.builder()
            .id(UUID.randomUUID().toString())
            .username(username)
            .password(hashedPWD)
            .active(true).build();
        AppUser savedAppUser = appUserRepository.save(newAppUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String name, String description) {
        AppRole appRole = appRoleRepository.findByName(name);
        if(appRole != null) throw new RuntimeException("Role"+name+" already exist");
        appRole = AppRole.builder().name(name).description(description).build();
        AppRole savedAppRole = appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        var appUser = appUserRepository.findByUsername(username);
        if(appUser == null) throw new RuntimeException("User not found");
        var appRole = appRoleRepository.findByName(roleName);
        if(appRole == null) throw new RuntimeException("Role not found");
        
        appUser.getRoles().add(appRole);
        
    }

    @Override
    public void removeRoleFromuser(String username, String roleName) {
        var appUser = appUserRepository.findByUsername(username);
        if(appUser == null) throw new RuntimeException("User not found");
        var appRole = appRoleRepository.findByName(roleName);
        if(appRole == null) throw new RuntimeException("Role not found");
        
        appUser.getRoles().remove(appRole);
        
    }

    @Override
    public AppUser loadUserByUserName(String username) {
        AppUser appUser = appUserRepository.findByUsername(username);
        return appUser;
    }
    
}
