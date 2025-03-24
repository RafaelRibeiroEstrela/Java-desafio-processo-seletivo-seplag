package com.example.appdesafioprocessoseletivoseplag.controllers;

import com.example.appdesafioprocessoseletivoseplag.mappers.UnidadeMapper;
import com.example.appdesafioprocessoseletivoseplag.utils.PageUtil;
import com.example.models.Unidade;
import com.example.models.filters.UnidadeFilter;
import com.example.providers.data.CustomPage;
import com.example.services.UnidadeService;
import com.example.unidade.UnidadeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/unidades")
public class UnidadeController {

    private final UnidadeService service;
    private final UnidadeMapper mapper;

    public UnidadeController(UnidadeService service, UnidadeMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    @PostMapping("/paginado")
    public Page<UnidadeDTO> findByFilter(@RequestBody UnidadeFilter filter, Pageable pageable) {
        CustomPage<Unidade> models = service.findByFilter(filter, PageUtil.toCustomPageable(pageable));
        List<UnidadeDTO> responses = models.getContent().stream().map(mapper::modelToResponse).toList();
        return new PageImpl<>(responses, pageable, models.getTotalElements());
    }

    @Transactional
    @PostMapping
    public UnidadeDTO create(@RequestBody UnidadeDTO request) {
        Unidade unidade = service.create(mapper.requestToModel(request));
        return mapper.modelToResponse(unidade);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Transactional(readOnly = true)
    @GetMapping
    public List<UnidadeDTO> findAll() {
        List<Unidade> models = service.findAll();
        return models.stream().map(mapper::modelToResponse).toList();
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public UnidadeDTO findById(@PathVariable Long id) {
        Unidade unidade = service.findById(id);
        return mapper.modelToResponse(unidade);
    }

    @Transactional
    @PutMapping("/{id}")
    public UnidadeDTO update(@RequestBody UnidadeDTO request, @PathVariable Long id) {
        Unidade unidade = service.update(mapper.requestToModel(request), id);
        return mapper.modelToResponse(unidade);
    }
}
