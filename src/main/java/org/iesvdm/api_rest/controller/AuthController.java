package org.iesvdm.api_rest.controller;

import jakarta.validation.Valid;
import org.iesvdm.api_rest.domain.*;
import org.iesvdm.api_rest.repository.UserRepository;
import org.iesvdm.api_rest.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Map<String, Object> response = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email ya registrado"));
        }else{
            Map<String, Object> response = authService.registerUser(registerRequest);
            return ResponseEntity.ok(response);
        }
    }

}
