package com.example.appdesafioprocessoseletivoseplag.controllers;

import com.example.appdesafioprocessoseletivoseplag.mappers.CidadeMapper;
import com.example.appdesafioprocessoseletivoseplag.utils.PageUtil;
import com.example.cidade.CidadeRequest;
import com.example.cidade.CidadeResponse;
import com.example.models.Cidade;
import com.example.models.enums.UfEnum;
import com.example.models.filters.CidadeFilter;
import com.example.providers.data.CustomPage;
import com.example.services.CidadeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cidades")
public class CidadeController {
    
    private final CidadeService service;
    private final CidadeMapper mapper;

    public CidadeController(CidadeService service, CidadeMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    @PostMapping("/paginado")
    public Page<CidadeResponse> findByFilter(CidadeFilter filter, Pageable pageable) {
        CustomPage<Cidade> models = service.findByFilter(filter, PageUtil.toCustomPageable(pageable));
        List<CidadeResponse> dtos = models.getContent().stream().map(mapper::modelToResponse).toList();
        return new PageImpl<>(dtos, pageable, models.getTotalElements());
    }

    @Transactional
    @PostMapping
    public CidadeResponse create(@RequestBody CidadeRequest request) {
        Cidade model = service.create(mapper.requestToModel(request));
        return mapper.modelToResponse(model);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Transactional(readOnly = true)
    @GetMapping
    public List<CidadeResponse> findAll() {
        List<Cidade> models = service.findAll();
        return models.stream().map(mapper::modelToResponse).toList();
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public CidadeResponse findById(@PathVariable Long id) {
        Cidade model = service.findById(id);
        return mapper.modelToResponse(model);
    }

    @Transactional
    @PutMapping("/{id}")
    public CidadeResponse update(@RequestBody CidadeRequest cidade, @PathVariable Long id) {
        Cidade model = service.update(mapper.requestToModel(cidade), id);
        return mapper.modelToResponse(model);
    }

    @GetMapping("/ufs")
    public UfEnum[] findAllUfs() {
        return UfEnum.values();
    }
}
