package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.ServidorEfetivoDTO;
import com.example.desafioprocessoseletivoseplagapi.services.ServidorEfetivoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/servidores-efetivos")
public class ServidorEfetivoController {

    private final ServidorEfetivoService service;

    public ServidorEfetivoController(ServidorEfetivoService service) {
        this.service = service;
    }

    @Transactional
    @PostMapping
    public ServidorEfetivoDTO create(@RequestBody ServidorEfetivoDTO dto) {
        return service.create(dto);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    @Transactional(readOnly = true)
    @GetMapping
    public List<ServidorEfetivoDTO> findAll() {
        return service.findAll();
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ServidorEfetivoDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Transactional
    @PutMapping("/{id}")
    public ServidorEfetivoDTO update(@RequestBody ServidorEfetivoDTO dto, @PathVariable Long id) {
        return service.update(dto, id);
    }
}
