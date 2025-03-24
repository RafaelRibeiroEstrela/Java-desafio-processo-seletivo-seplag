package com.example.services.impl;

import com.example.models.Endereco;
import com.example.models.Unidade;
import com.example.models.UnidadeEndereco;
import com.example.models.filters.UnidadeFilter;
import com.example.ports.UnidadeEnderecoPort;
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
    private final UnidadeEnderecoPort unidadeEnderecoPort;

    private final EnderecoService enderecoService;

    public UnidadeServiceImpl(UnidadePort port, UnidadeEnderecoPort unidadeEnderecoPort, EnderecoService enderecoService) {
        this.port = port;
        this.unidadeEnderecoPort = unidadeEnderecoPort;
        this.enderecoService = enderecoService;
    }

    @Override
    public CustomPage<Unidade> findByFilter(UnidadeFilter filter, CustomPageable pageable) {
        return port.findByFilter(filter, pageable);
    }

    @Override
    public Unidade create(Unidade unidade) {
        validarCamposObrigatorios(unidade);
        List<Endereco> enderecos = unidade.getEnderecos();
        unidade = port.save(unidade);
        unidade.setEnderecos(enderecos.stream().map(enderecoService::create).toList());
        for (Endereco endereco : unidade.getEnderecos()) {
            unidadeEnderecoPort.create(new UnidadeEndereco(unidade.getId(), endereco.getId()));
        }
        return unidade;
    }

    @Override
    public void delete(Long id) {
        //VALIDAR SE ALGUEM ESTA USANDO
        List<UnidadeEndereco> unidadeEnderecoList = unidadeEnderecoPort.findByUnidadeId(id);
        unidadeEnderecoPort.deleteByUnidadeId(id);
        unidadeEnderecoList.forEach(unidadeEndereco -> enderecoService.delete(unidadeEndereco.getEnderecoId()));
        port.delete(id);
    }

    @Override
    public List<Unidade> findAll() {
        return port.findAll();
    }

    @Override
    public Unidade findById(Long id) {
        Unidade unidade = port.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhuma unidade encontrada", this));
        List<UnidadeEndereco> unidadeEnderecoList = unidadeEnderecoPort.findByUnidadeId(id);
        unidade.setEnderecos(unidadeEnderecoList.stream().map(unidadeEndereco -> enderecoService.findById(unidadeEndereco.getEnderecoId())).toList());
        return unidade;
    }

    @Override
    public Unidade update(Unidade unidade, Long id) {
        if (!port.existsById(id)) {
            throw new ResourceNotFoundException("Nenhuma unidade encontrada", this);
        }
        validarCamposObrigatorios(unidade);
        List<UnidadeEndereco> unidadeEnderecoList = unidadeEnderecoPort.findByUnidadeId(id);
        unidadeEnderecoPort.deleteByUnidadeId(id);
        unidadeEnderecoList.forEach(unidadeEndereco -> enderecoService.delete(unidadeEndereco.getEnderecoId()));
        unidade.setEnderecos(unidade.getEnderecos().stream().map(this::atualizarEndereco).toList());
        unidade.getEnderecos().forEach(endereco -> unidadeEnderecoPort.create(new UnidadeEndereco(id, endereco.getId())));
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
