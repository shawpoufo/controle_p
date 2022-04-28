package com.controle.patient.security.repositories;

import com.controle.patient.security.entities.AppRole;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByName(String name);
    
}
