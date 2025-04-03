package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.LotacaoDTO;
import com.example.desafioprocessoseletivoseplagapi.models.filters.LotacaoFilter;
import com.example.desafioprocessoseletivoseplagapi.services.LotacaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/lotacoes")
public class LotacaoController {
    
    private final LotacaoService service;

    public LotacaoController(LotacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LotacaoDTO> create(@RequestBody LotacaoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public List<LotacaoDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public LotacaoDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public LotacaoDTO update(@RequestBody LotacaoDTO dto, @PathVariable Long id) {
        return service.update(dto, id);
    }

    @PostMapping("/paginado")
    public Page<LotacaoDTO> findByFilter(@RequestBody LotacaoFilter filter, Pageable pageable) {
        return service.findByFilter(filter, pageable);
    }
}
