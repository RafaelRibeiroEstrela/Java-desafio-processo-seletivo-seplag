package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.CidadeDTO;
import com.example.desafioprocessoseletivoseplagapi.models.enums.UfEnum;
import com.example.desafioprocessoseletivoseplagapi.models.filters.CidadeFilter;
import com.example.desafioprocessoseletivoseplagapi.services.CidadeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cidades")
public class CidadeController {
    
    private final CidadeService service;

    public CidadeController(CidadeService service) {
        this.service = service;
    }

    @Transactional(readOnly = true)
    @PostMapping("/paginado")
    public Page<CidadeDTO> findByFilter(@RequestBody CidadeFilter filter, Pageable pageable) {
        return service.findByFilter(filter, pageable);
    }

    @Transactional
    @PostMapping
    public CidadeDTO create(@RequestBody @Valid CidadeDTO request) {
        return service.create(request);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Transactional(readOnly = true)
    @GetMapping
    public List<CidadeDTO> findAll() {
        return service.findAll();
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public CidadeDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Transactional
    @PutMapping("/{id}")
    public CidadeDTO update(@RequestBody @Valid CidadeDTO request, @PathVariable Long id) {
        return service.update(request, id);
    }

    @GetMapping("/ufs")
    public UfEnum[] findAllUfs() {
        return UfEnum.values();
    }
}
