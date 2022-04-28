package com.controle.patient.security.repositories;

import com.controle.patient.security.entities.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
    
}
