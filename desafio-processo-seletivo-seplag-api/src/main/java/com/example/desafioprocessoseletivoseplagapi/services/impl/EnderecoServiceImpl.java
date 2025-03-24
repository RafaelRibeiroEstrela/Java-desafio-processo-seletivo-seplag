package com.example.desafioprocessoseletivoseplagapi.services.impl;


import com.example.desafioprocessoseletivoseplagapi.dtos.CidadeDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.EnderecoDTO;
import com.example.desafioprocessoseletivoseplagapi.models.Endereco;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.BusinessException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.LayerDefinition;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.ResourceNotFoundException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;
import com.example.desafioprocessoseletivoseplagapi.repositories.EnderecoRepository;
import com.example.desafioprocessoseletivoseplagapi.services.CidadeService;
import com.example.desafioprocessoseletivoseplagapi.services.EnderecoService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService, LayerDefinition {

    private final EnderecoRepository repository;

    private final CidadeService cidadeService;

    public EnderecoServiceImpl(EnderecoRepository repository, CidadeService cidadeService) {
        this.repository = repository;
        this.cidadeService = cidadeService;
    }

    @Override
    public EnderecoDTO create(EnderecoDTO dto) {
        validarCamposObrigatorios(dto);
        Endereco model = dto.toModel();
        model = repository.save(model);
        return new EnderecoDTO(model);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<EnderecoDTO> findAll() {
        return repository.findAll().stream().map(EnderecoDTO::new).toList();
    }

    @Override
    public EnderecoDTO findById(Long id) {
        Endereco model = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum endereco encontrado", this));
        CidadeDTO cidadeDTO = cidadeService.findById(model.getCidadeId());
        EnderecoDTO dto = new EnderecoDTO(model);
        dto.setCidade(cidadeDTO);
        return dto;
    }

    @Override
    public EnderecoDTO update(EnderecoDTO enderecoDTO, Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Nenhum endereco encontrado", this);
        }
        Endereco model = enderecoDTO.toModel();
        model.setId(id);
        model = repository.save(model);
        return new EnderecoDTO(model);
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_COMPONENT;
    }

    private void validarCamposObrigatorios(EnderecoDTO endereco) {
        if (endereco.getTipoLogradouro() == null) {
            throw new BusinessException("O tipo de logradouro é obrigatório", this);
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
}
