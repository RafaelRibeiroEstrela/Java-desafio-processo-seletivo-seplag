package com.example.services.impl;

import com.example.models.Endereco;
import com.example.models.Unidade;
import com.example.models.filters.UnidadeFilter;
import com.example.ports.UnidadePort;
import com.example.providers.data.CustomPage;
import com.example.providers.data.CustomPageable;
import com.example.providers.exceptions.BusinessException;
import com.example.providers.exceptions.LayerDefinition;
import com.example.providers.exceptions.ResourceNotFoundException;
import com.example.providers.exceptions.enums.LayerEnum;
import com.example.services.EnderecoService;
import com.example.services.UnidadeService;

import java.util.List;

public class UnidadeServiceImpl implements UnidadeService, LayerDefinition {
    
    private final UnidadePort port;

    private final EnderecoService enderecoService;

    public UnidadeServiceImpl(UnidadePort port, EnderecoService enderecoService) {
        this.port = port;
        this.enderecoService = enderecoService;
    }

    @Override
    public CustomPage<Unidade> findByFilter(UnidadeFilter filter, CustomPageable pageable) {
        return port.findByFilter(filter, pageable);
    }

    @Override
    public Unidade create(Unidade unidade) {
        validarCamposObrigatorios(unidade);
        unidade.setEnderecos(unidade.getEnderecos().stream().map(enderecoService::create).toList());
        return port.save(unidade);
    }

    @Override
    public void delete(Long id) {
        //VALIDAR SE ALGUEM ESTA USANDO
        port.delete(id);
    }

    @Override
    public List<Unidade> findAll() {
        return port.findAll();
    }

    @Override
    public Unidade findById(Long id) {
        return port.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhuma unidade encontrada", this));
    }

    @Override
    public Unidade update(Unidade unidade, Long id) {
        validarCamposObrigatorios(unidade);
        Unidade unidadeSalva = findById(id);
        unidadeSalva.getEnderecos().forEach(endereco -> enderecoService.delete(endereco.getId()));
        unidade.setEnderecos(unidade.getEnderecos().stream().map(this::atualizarEndereco).toList());
        unidade.setId(id);
        return port.save(unidade);
    }

    private Endereco atualizarEndereco(Endereco endereco) {
        if (endereco.getId() == null) {
            return enderecoService.create(endereco);
        }
        return enderecoService.update(endereco, endereco.getId());
    }

    private void validarCamposObrigatorios(Unidade unidade) {
        if (unidade.getNome() == null || unidade.getNome().isEmpty()) {
            throw new BusinessException("O nome da unidade é obrigatório", this);
        }
        if (unidade.getSigla() == null || unidade.getSigla().isEmpty()) {
            throw new BusinessException("A sigla da unidade é obrigatório", this);
        }
        if (unidade.getEnderecos().isEmpty()) {
            throw new BusinessException("O endereco da unidade é obrigatório", this);
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
