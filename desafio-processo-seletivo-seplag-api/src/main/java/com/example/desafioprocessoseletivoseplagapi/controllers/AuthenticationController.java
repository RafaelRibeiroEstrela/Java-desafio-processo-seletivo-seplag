package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.AuthenticationDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.LoginDTO;
import com.example.desafioprocessoseletivoseplagapi.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "authentication-controller", description = "Operações de autenticação")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @Operation(summary = "Autenticação do usuário", description = "Realiza login com username e password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginDTO.class))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public LoginDTO login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Credenciais de autenticação",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AuthenticationDTO.class))
            )
            @RequestBody AuthenticationDTO dto) {
        return service.login(dto);
    }

    @Operation(summary = "Atualizar token de acesso", description = "Atualiza o token JWT expirado usando um token válido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginDTO.class))),
            @ApiResponse(responseCode = "401", description = "Token inválido ou expirado")
    })
    @PostMapping("/refresh-token")
    public LoginDTO refreshToken(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Token de autenticação atual",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LoginDTO.class))
            )
            @RequestBody LoginDTO dto) {
        return service.refreshToken(dto);
    }

    @Operation(summary = "Logout do usuário", description = "Realiza o logout invalidando o token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logout realizado com sucesso")
    })
    @PostMapping("/logout")
    public void logout(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Token atual para logout",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LoginDTO.class))
            )
            @RequestBody LoginDTO dto) {
        service.logout(dto);
    }

}
