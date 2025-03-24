package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.PessoaDTO;
import com.example.desafioprocessoseletivoseplagapi.models.filters.PessoaFilter;
import com.example.desafioprocessoseletivoseplagapi.services.PessoaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pessoas")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @Transactional(readOnly = true)
    @PostMapping("/paginado")
    public Page<PessoaDTO> findByFilter(@RequestBody PessoaFilter filter, Pageable pageable) {
        return service.findByFilter(filter, pageable);
    }

    @Transactional
    @PostMapping
    public PessoaDTO create(@RequestBody PessoaDTO dto) {
        return service.create(dto);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Transactional(readOnly = true)
    @GetMapping
    public List<PessoaDTO> findAll() {
        return service.findAll();
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public PessoaDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Transactional
    @PutMapping("/{id}")
    public PessoaDTO update(@RequestBody PessoaDTO dto, @PathVariable Long id) {
        return service.update(dto, id);
    }
}
