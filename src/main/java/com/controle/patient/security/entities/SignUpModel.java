package com.controle.patient.security.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class SignUpModel {
    @NotEmpty @Size(min = 3 , max = 30)
    private String username;
    @NotEmpty @Size(min = 6,max = 20)
    private String password;
    @NotEmpty @Size(min = 6,max = 20)
    private String rePassword;
}
