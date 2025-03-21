package com.example.appdesafioprocessoseletivoseplag.controllers;

import com.example.appdesafioprocessoseletivoseplag.dtos.requests.CidadeRequest;
import com.example.appdesafioprocessoseletivoseplag.dtos.responses.CidadeResponse;
import com.example.appdesafioprocessoseletivoseplag.mappers.CidadeMapper;
import com.example.appdesafioprocessoseletivoseplag.utils.PageUtil;
import com.example.models.Cidade;
import com.example.models.filters.CidadeFilter;
import com.example.providers.data.CustomPage;
import com.example.services.CidadeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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


    @PostMapping("/paginado")
    public Page<CidadeResponse> findByFilter(CidadeFilter filter, Pageable pageable) {
        CustomPage<Cidade> models = service.findByFilter(filter, PageUtil.toCustomPageable(pageable));
        List<CidadeResponse> dtos = models.getContent().stream().map(mapper::modelToResponse).toList();
        return new PageImpl<>(dtos, pageable, models.getTotalElements());
    }

    @PostMapping
    public CidadeResponse create(@RequestBody CidadeRequest request) {
        Cidade model = service.create(mapper.requestToModel(request));
        return mapper.modelToResponse(model);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping
    public List<CidadeResponse> findAll() {
        List<Cidade> models = service.findAll();
        return models.stream().map(mapper::modelToResponse).toList();
    }

    @GetMapping("/{id}")
    public CidadeResponse findById(@PathVariable Long id) {
        Cidade model = service.findById(id);
        return mapper.modelToResponse(model);
    }

    @PutMapping("/{id}")
    public CidadeResponse update(@RequestBody CidadeRequest cidade, @PathVariable Long id) {
        Cidade model = service.update(mapper.requestToModel(cidade), id);
        return mapper.modelToResponse(model);
    }
}
