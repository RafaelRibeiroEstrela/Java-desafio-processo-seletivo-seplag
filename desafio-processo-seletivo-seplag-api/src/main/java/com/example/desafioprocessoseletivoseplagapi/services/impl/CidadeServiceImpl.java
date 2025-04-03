package com.example.desafioprocessoseletivoseplagapi.services.impl;


import com.example.desafioprocessoseletivoseplagapi.dtos.CidadeDTO;
import com.example.desafioprocessoseletivoseplagapi.models.Cidade;
import com.example.desafioprocessoseletivoseplagapi.models.filters.CidadeFilter;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.BusinessException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.LayerDefinition;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.ResourceNotFoundException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;
import com.example.desafioprocessoseletivoseplagapi.repositories.CidadeRepository;
import com.example.desafioprocessoseletivoseplagapi.services.CidadeService;
import com.example.desafioprocessoseletivoseplagapi.utils.QueryUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeServiceImpl implements CidadeService, LayerDefinition {

    private final CidadeRepository repository;

    public CidadeServiceImpl(CidadeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<CidadeDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(CidadeDTO::new);
    }

    @Override
    public CidadeDTO create(CidadeDTO dto) {
        validarCamposObrigatorios(dto);
        Cidade model = dto.toModel();
        model = repository.save(model);
        return new CidadeDTO(model);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CidadeDTO> findAll() {
        return repository.findAll().stream().map(CidadeDTO::new).toList();
    }

    @Override
    public CidadeDTO findById(Long id) {
        return repository.findById(id).map(CidadeDTO::new).orElseThrow(() -> new ResourceNotFoundException("Nenhuma cidade encontrada", this));
    }

    @Override
    public CidadeDTO update(CidadeDTO dto, Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Nenhuma cidade encontrada", this);
        }
        validarCamposObrigatorios(dto);
        Cidade model = dto.toModel();
        model.setId(id);
        model = repository.save(model);
        return new CidadeDTO(model);
    }

    private void validarCamposObrigatorios(CidadeDTO dto) {
        if (dto.getNome() == null || dto.getNome().trim().isEmpty()) {
            throw new BusinessException("O nome da cidade é obrigatório", this);
        }
        if (dto.getUf() == null) {
            throw new BusinessException("A unidade federativa da cidade é obrigatório", this);
        }
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_COMPONENT;
    }


}
