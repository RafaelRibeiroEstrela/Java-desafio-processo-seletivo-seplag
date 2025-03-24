package com.example.desafioprocessoseletivoseplagapi.services.impl;


import com.example.desafioprocessoseletivoseplagapi.dtos.EnderecoDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.UnidadeDTO;
import com.example.desafioprocessoseletivoseplagapi.models.Unidade;
import com.example.desafioprocessoseletivoseplagapi.models.UnidadeEndereco;
import com.example.desafioprocessoseletivoseplagapi.models.filters.UnidadeFilter;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.BusinessException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.LayerDefinition;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.ResourceNotFoundException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;
import com.example.desafioprocessoseletivoseplagapi.repositories.UnidadeEnderecoRepository;
import com.example.desafioprocessoseletivoseplagapi.repositories.UnidadeRepository;
import com.example.desafioprocessoseletivoseplagapi.services.EnderecoService;
import com.example.desafioprocessoseletivoseplagapi.services.UnidadeService;
import com.example.desafioprocessoseletivoseplagapi.utils.QueryUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeServiceImpl implements UnidadeService, LayerDefinition {

    private final UnidadeRepository repository;

    private final EnderecoService enderecoService;
    private final UnidadeEnderecoRepository unidadeEnderecoRepository;

    public UnidadeServiceImpl(UnidadeRepository repository, EnderecoService enderecoService, UnidadeEnderecoRepository unidadeEnderecoRepository) {
        this.repository = repository;
        this.enderecoService = enderecoService;
        this.unidadeEnderecoRepository = unidadeEnderecoRepository;
    }

    @Override
    public Page<UnidadeDTO> findByFilter(UnidadeFilter filter, Pageable pageable) {
        return repository
                .findByFilter(QueryUtil.aplicarLetraMaiusculaEColocarEntreCoringas(filter.getNome()), QueryUtil.aplicarLetraMaiusculaEColocarEntreCoringas(filter.getSigla()), pageable)
                .map(UnidadeDTO::new);
    }

    @Override
    public UnidadeDTO create(UnidadeDTO dto) {
        validarCamposObrigatorios(dto);
        List<EnderecoDTO> enderecoDTOList = dto.getEnderecos().stream().map(enderecoService::create).toList();
        Unidade model = dto.toModel();
        model = repository.save(model);
        for (EnderecoDTO enderecoDTO : enderecoDTOList) {
            unidadeEnderecoRepository.save(new UnidadeEndereco(model.getId(), enderecoDTO.getId()));
        }
        return new UnidadeDTO(model);
    }

    @Override
    public void delete(Long id) {
        List<UnidadeEndereco> unidadeEnderecoList = unidadeEnderecoRepository.findByUnidadeId(id);
        unidadeEnderecoRepository.deleteByUnidadeId(id);
        unidadeEnderecoList.forEach(unidadeEndereco -> enderecoService.delete(unidadeEndereco.getId().getEnderecoId()));
        repository.deleteById(id);
    }

    @Override
    public List<UnidadeDTO> findAll() {
        return repository.findAll().stream().map(UnidadeDTO::new).toList();
    }

    @Override
    public UnidadeDTO findById(Long id) {
        Unidade model = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhuma unidade encontrada", this));
        List<UnidadeEndereco> unidadeEnderecoList = unidadeEnderecoRepository.findByUnidadeId(id);
        List<EnderecoDTO> enderecoDTOList = unidadeEnderecoList.stream().map(unidadeEndereco -> enderecoService.findById(unidadeEndereco.getId().getEnderecoId())).toList();
        UnidadeDTO dto = new UnidadeDTO(model);
        dto.setEnderecos(enderecoDTOList);
        return dto;
    }

    @Override
    public UnidadeDTO update(UnidadeDTO dto, Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Nenhuma unidade encontrada", this);
        }
        validarCamposObrigatorios(dto);
        atualizarEndereco(dto, id);
        Unidade model = dto.toModel();
        model.setId(id);
        model = repository.save(model);
        return new UnidadeDTO(model);
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_COMPONENT;
    }

    private void validarCamposObrigatorios(UnidadeDTO unidade) {
        if (unidade.getEnderecos().isEmpty()) {
            throw new BusinessException("O endereco da unidade é obrigatório", this);
        }
    }

    private void atualizarEndereco(UnidadeDTO dto, long id) {
        List<UnidadeEndereco> unidadeEnderecoList = unidadeEnderecoRepository.findByUnidadeId(id);
        unidadeEnderecoRepository.deleteByUnidadeId(id);
        unidadeEnderecoList.forEach(unidadeEndereco -> enderecoService.delete(unidadeEndereco.getId().getEnderecoId()));
        List<EnderecoDTO> enderecoDTOList = dto.getEnderecos().stream().map(enderecoService::create).toList();
        enderecoDTOList.forEach(enderecoDTO -> unidadeEnderecoRepository.save(new UnidadeEndereco(id, enderecoDTO.getId())));
    }
}
