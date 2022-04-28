package com.controle.patient.security.services;

import com.controle.patient.security.entities.AppRole;
import com.controle.patient.security.entities.AppUser;

public interface ISecurityService {
    AppUser saveNewUser(String username,String password,String rePassword);
    AppRole saveNewRole(String name,String description); 
    void addRoleToUser(String username , String roleName);
    void removeRoleFromuser(String username,String roleName);
    AppUser loadUserByUserName(String username);
}
