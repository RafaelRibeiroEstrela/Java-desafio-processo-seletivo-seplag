package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.LotacaoDTO;
import com.example.desafioprocessoseletivoseplagapi.services.LotacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lotacoes")
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

    @GetMapping("/unidade/{unidadeId}")
    public List<LotacaoDTO> findServidoresEfetivosByUnidadeId(@PathVariable Long unidadeId) {
        return service.findServidoresEfetivosByUnidadeId(unidadeId);
    }
}
