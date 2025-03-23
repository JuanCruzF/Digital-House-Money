package com.juanc.controllers;


import com.juanc.dto.User;
import com.juanc.services.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class KeycloakUserApi {
    private final KeycloakUserService keycloakUserService;
    @GetMapping
    public UserRepresentation getUser(Principal principal){
        return keycloakUserService.getUserById(principal.getName());
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return keycloakUserService.createUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId){
        keycloakUserService.deleteUserById(userId);
    }
}
