package com.example.services.impl;

import com.example.models.Endereco;
import com.example.models.enums.TipoLogradouroEnum;
import com.example.ports.EnderecoPort;
import com.example.providers.exceptions.BusinessException;
import com.example.providers.exceptions.LayerDefinition;
import com.example.providers.exceptions.ResourceNotFoundException;
import com.example.providers.exceptions.enums.LayerEnum;
import com.example.services.EnderecoService;

import java.util.List;

public class EnderecoServiceImpl implements EnderecoService, LayerDefinition {

    private final EnderecoPort port;

    public EnderecoServiceImpl(EnderecoPort port) {
        this.port = port;
    }

    @Override
    public Endereco create(Endereco endereco) {
        validarCamposObrigatorios(endereco);
        return port.save(endereco);
    }

    @Override
    public void delete(Long id) {
        //VALIDAR SE ALGUEM ESTA USANDO
        port.delete(id);
    }

    @Override
    public List<Endereco> findAll() {
        return port.findAll();
    }

    @Override
    public Endereco findById(Long id) {
        return port.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum endereco encontrado", this));
    }

    @Override
    public Endereco update(Endereco endereco, Long id) {
        validarCamposObrigatorios(endereco);
        endereco.setId(id);
        return port.save(endereco);
    }

    private void validarCamposObrigatorios(Endereco endereco) {
        if (endereco.getTipoLogradouro() == null) {
            throw new BusinessException("O tipo de logradouro é obrigatório", this);
        }
        try {
            TipoLogradouroEnum.toEnum(endereco.getTipoLogradouro());
        } catch (IllegalArgumentException e) {
            throw new BusinessException("O tipo de logradouro é inválido", this);
        }
        if (endereco.getLogradouro() == null || endereco.getLogradouro().isEmpty()) {
            throw new BusinessException("O logradouro é obrigatório", this);
        }
        if (endereco.getNumero() == null || endereco.getNumero().isEmpty()) {
            throw new BusinessException("O numero do endereco é obrigatório", this);
        }
        if (endereco.getBairro() == null || endereco.getBairro().isEmpty()) {
            throw new BusinessException("O bairro é obrigatório", this);
        }
        if (endereco.getCidade() == null || endereco.getCidade().getId() == null) {
            throw new BusinessException("A cidade é obrigatória", this);
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
