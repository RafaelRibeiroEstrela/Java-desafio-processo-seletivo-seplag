package com.example.desafioprocessoseletivoseplagapi.services.impl;

import com.example.desafioprocessoseletivoseplagapi.components.TokenManagment;
import com.example.desafioprocessoseletivoseplagapi.dtos.AuthenticationDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.LoginDTO;
import com.example.desafioprocessoseletivoseplagapi.models.User;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.BusinessException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.LayerDefinition;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;
import com.example.desafioprocessoseletivoseplagapi.services.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService, LayerDefinition {

    private final AuthenticationManager authenticationManager;
    private final TokenManagment tokenManagment;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, TokenManagment tokenManagment) {
        this.authenticationManager = authenticationManager;
        this.tokenManagment = tokenManagment;
    }

    @Override
    public LoginDTO login(AuthenticationDTO dto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token = tokenManagment.generate((User) auth.getPrincipal());
        return new LoginDTO(token);
    }

    @Override
    public void logout(LoginDTO loginDTO) {
        if (loginDTO.getToken() == null || loginDTO.getToken().isEmpty()) {
            throw new BusinessException("O token é obrigatório", this);
        }
        tokenManagment.addTokenInBlackList(loginDTO.getToken());
    }

    public void validarCamposObrigatorios(AuthenticationDTO authenticationDTO) {
        if (authenticationDTO.getUsername() == null || authenticationDTO.getUsername().isEmpty()) {
            throw new BusinessException("O username é obrigatório", this);
        }
        if (authenticationDTO.getPassword() == null || authenticationDTO.getPassword().isEmpty()) {
            throw new BusinessException("A senha é obrigatório", this);
        }
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_COMPONENT;
    }
}
