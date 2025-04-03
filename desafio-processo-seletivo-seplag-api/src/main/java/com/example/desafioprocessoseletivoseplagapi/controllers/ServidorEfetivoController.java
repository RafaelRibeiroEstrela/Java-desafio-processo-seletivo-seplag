package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.ServidorEfetivoDTO;
import com.example.desafioprocessoseletivoseplagapi.services.ServidorEfetivoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ServidorEfetivoDTO> create(@RequestBody ServidorEfetivoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Transactional(readOnly = true)
    @GetMapping
    public Page<ServidorEfetivoDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
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
