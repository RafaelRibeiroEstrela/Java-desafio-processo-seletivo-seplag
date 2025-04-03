package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.UnidadeDTO;
import com.example.desafioprocessoseletivoseplagapi.models.filters.UnidadeFilter;
import com.example.desafioprocessoseletivoseplagapi.services.UnidadeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/unidades")
public class UnidadeController {

    private final UnidadeService service;

    public UnidadeController(UnidadeService service) {
        this.service = service;
    }

    @Transactional(readOnly = true)
    @PostMapping("/paginado")
    public Page<UnidadeDTO> finAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<UnidadeDTO> create(@RequestBody @Valid UnidadeDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public UnidadeDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Transactional
    @PutMapping("/{id}")
    public UnidadeDTO update(@RequestBody @Valid UnidadeDTO request, @PathVariable Long id) {
        return service.update(request, id);
    }
}
