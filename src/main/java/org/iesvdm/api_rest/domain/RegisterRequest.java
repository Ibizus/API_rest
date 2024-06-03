package org.iesvdm.api_rest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private Set<String> roles;

}
