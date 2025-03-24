package com.example.services.impl;

import com.example.models.Cidade;
import com.example.models.enums.UfEnum;
import com.example.models.filters.CidadeFilter;
import com.example.ports.CidadePort;
import com.example.providers.data.CustomPage;
import com.example.providers.data.CustomPageable;
import com.example.providers.exceptions.BusinessException;
import com.example.providers.exceptions.LayerDefinition;
import com.example.providers.exceptions.ResourceNotFoundException;
import com.example.providers.exceptions.enums.LayerEnum;
import com.example.services.CidadeService;

import java.util.List;

public class CidadeServiceImpl implements CidadeService, LayerDefinition {

    private final CidadePort port;

    public CidadeServiceImpl(CidadePort port) {
        this.port = port;
    }

    @Override
    public CustomPage<Cidade> findByFilter(CidadeFilter filter, CustomPageable pageable) {
        return port.findByFilter(filter, pageable);
    }

    @Override
    public Cidade create(Cidade model) {
        validarCamposObrigatorios(model);
        return port.save(model);
    }

    @Override
    public void delete(Long id) {
        //verificar se alguem esta usando
        port.delete(id);
    }

    @Override
    public List<Cidade> findAll() {
        return port.findAll();
    }

    @Override
    public Cidade findById(Long id) {
        return port.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhuma cidade encontrada", this));
    }

    @Override
    public Cidade update(Cidade model, Long id) {
        if (!port.existsById(id)) {
            throw new ResourceNotFoundException("Nenhuma cidade encontrada", this);
        }
        validarCamposObrigatorios(model);
        model.setId(id);
        return port.save(model);
    }

    private void validarCamposObrigatorios(Cidade model) {
        if (model.getNome() == null || model.getNome().isEmpty()) {
            throw new BusinessException("O nome da cidade é obrigatório", this);
        }
        if (model.getUf() == null) {
            throw new BusinessException("A unidade federativa da cidade é obrigatório", this);
        }
        try {
            UfEnum.toEnum(model.getUf());
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Unidade federativa inválida", this);
        }
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.DOMAIN_SERVICE;
    }
}
