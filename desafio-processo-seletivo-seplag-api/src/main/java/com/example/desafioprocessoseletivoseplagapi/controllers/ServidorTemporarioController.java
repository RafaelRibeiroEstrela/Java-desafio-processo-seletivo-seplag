package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.ServidorTemporarioDTO;
import com.example.desafioprocessoseletivoseplagapi.services.ServidorTemporarioService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/servidores-temporarios")
public class ServidorTemporarioController {

    private final ServidorTemporarioService service;

    public ServidorTemporarioController(ServidorTemporarioService service) {
        this.service = service;
    }

    @Transactional
    @PostMapping
    public ServidorTemporarioDTO create(@RequestBody ServidorTemporarioDTO dto) {
        return service.create(dto);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    @Transactional(readOnly = true)
    @GetMapping
    public List<ServidorTemporarioDTO> findAll() {
        return service.findAll();
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ServidorTemporarioDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Transactional
    @PutMapping("/{id}")
    public ServidorTemporarioDTO update(@RequestBody ServidorTemporarioDTO dto, @PathVariable Long id) {
        return service.update(dto, id);
    }
}
