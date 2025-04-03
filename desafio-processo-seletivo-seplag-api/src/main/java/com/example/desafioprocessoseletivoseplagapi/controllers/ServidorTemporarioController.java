package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.ServidorTemporarioDTO;
import com.example.desafioprocessoseletivoseplagapi.services.ServidorTemporarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ServidorTemporarioDTO> create(@RequestBody ServidorTemporarioDTO dto) {
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
    public Page<ServidorTemporarioDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
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
