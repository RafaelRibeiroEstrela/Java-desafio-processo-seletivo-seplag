package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.AuthenticationDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.LoginDTO;
import com.example.desafioprocessoseletivoseplagapi.services.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public LoginDTO login(@RequestBody AuthenticationDTO dto) {
        return service.login(dto);
    }

    @PostMapping("/refresh-token")
    public LoginDTO refreshToken(@RequestBody LoginDTO dto) {
        return service.refreshToken(dto);
    }

    @PostMapping("/logout")
    public void login(@RequestBody LoginDTO dto) {
        service.logout(dto);
    }

}
