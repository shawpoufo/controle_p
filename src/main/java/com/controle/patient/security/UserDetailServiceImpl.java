package com.controle.patient.security;

import java.util.ArrayList;
import java.util.Collection;

import com.controle.patient.security.entities.AppUser;
import com.controle.patient.security.services.ISecurityService;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@AllArgsConstructor @Service @Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    private ISecurityService SecurityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = SecurityService.loadUserByUserName(username);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role -> {
           var authority = new SimpleGrantedAuthority(role.getName());
           authorities.add(authority);
        });
        User user = new User(appUser.getUsername(), appUser.getPassword(), authorities);
        return user;
    }
    
}
