package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.models.enums.TipoLogradouroEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/enderecos")
public class EnderecoController {

    @GetMapping("/tipos-de-logradouro")
    public TipoLogradouroEnum[] getTiposLogradouro() {
        return TipoLogradouroEnum.values();
    }
}
