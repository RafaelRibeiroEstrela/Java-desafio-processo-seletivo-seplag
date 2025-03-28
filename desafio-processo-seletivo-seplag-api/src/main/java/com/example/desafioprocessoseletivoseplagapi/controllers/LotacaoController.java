package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.LotacaoDTO;
import com.example.desafioprocessoseletivoseplagapi.models.filters.LotacaoFilter;
import com.example.desafioprocessoseletivoseplagapi.services.LotacaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public LotacaoDTO create(@RequestBody LotacaoDTO dto) {
        return service.create(dto);
    }

    @DeleteMapping
    public void delete(Long id) {
        service.delete(id);
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
