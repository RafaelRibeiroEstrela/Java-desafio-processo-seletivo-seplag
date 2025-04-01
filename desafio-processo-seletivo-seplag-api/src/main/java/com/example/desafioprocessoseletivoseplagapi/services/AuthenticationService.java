package com.example.desafioprocessoseletivoseplagapi.services;

import com.example.desafioprocessoseletivoseplagapi.dtos.AuthenticationDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.LoginDTO;

public interface AuthenticationService {

    LoginDTO login(AuthenticationDTO authenticationDTO);
    LoginDTO refreshToken(LoginDTO loginDTO);
    void logout(LoginDTO loginDTO);
}
