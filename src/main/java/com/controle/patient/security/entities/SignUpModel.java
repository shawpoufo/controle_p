package com.controle.patient.security.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class SignUpModel {
    @NotEmpty(message = "username obligatoire") @Size(min = 3 , max = 30,message = "la taille doit être entre 3 et 30 caractères")
    private String username;
    @NotEmpty(message = "mot de passe obligatoire") @Size(min = 6,max = 20,message = "la taille doit être entre 6 et 20 caractères")
    private String password;
    @NotEmpty(message = "re-mot de passe obligatoire") @Size(min = 6,max = 20,message = "la taille doit être entre 6 et 20 caractères")
    private String rePassword;
}
