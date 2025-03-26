package com.example.desafioprocessoseletivoseplagapi.components;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.desafioprocessoseletivoseplagapi.models.User;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.LayerDefinition;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.TokenException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TokenManagment implements LayerDefinition {

    //A CHAVE DEVE SER ARMAZENADA EM ALGUM LOCAL OCULTO E COM SEGURANCA (EX: VARIAVEL DE AMBIENTE)
    private static final String SECRET_KEY = "XPTO";
    private static final String ISSUER = "spring-security-service";
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenManagment.class);

    public String generate(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withExpiresAt(expireToken())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            LOGGER.info("Erro ao gerar token: {}", e.getMessage());
            throw new TokenException("Erro ao gerar token. " + e.getMessage(), this);
        }
    }

    public String validadeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            LOGGER.info("Erro ao validar token: {}", e.getMessage());
            throw new TokenException("Erro ao validar token: " + e.getMessage(), this);
        }
    }

    private Instant expireToken() {
        //1h - 3600 segundos
        return Instant.now().plusSeconds(3600);
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
