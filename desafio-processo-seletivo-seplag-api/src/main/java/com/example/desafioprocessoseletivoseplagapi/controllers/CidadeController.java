package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.CidadeDTO;
import com.example.desafioprocessoseletivoseplagapi.services.CidadeService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/cidades")
public class CidadeController {
    
    private final CidadeService service;

    public CidadeController(CidadeService service) {
        this.service = service;
    }

    @Transactional(readOnly = true)
    @GetMapping
    public List<CidadeDTO> findAll() {
        return service.findAll();
    }
}
