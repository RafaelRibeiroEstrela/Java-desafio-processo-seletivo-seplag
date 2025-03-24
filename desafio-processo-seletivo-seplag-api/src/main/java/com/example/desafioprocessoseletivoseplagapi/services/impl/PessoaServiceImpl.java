package com.example.desafioprocessoseletivoseplagapi.services.impl;

import com.example.desafioprocessoseletivoseplagapi.dtos.PessoaDTO;
import com.example.desafioprocessoseletivoseplagapi.models.Pessoa;
import com.example.desafioprocessoseletivoseplagapi.models.filters.PessoaFilter;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.BusinessException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.LayerDefinition;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.ResourceNotFoundException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;
import com.example.desafioprocessoseletivoseplagapi.repositories.PessoaRepository;
import com.example.desafioprocessoseletivoseplagapi.services.PessoaService;
import com.example.desafioprocessoseletivoseplagapi.utils.QueryUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService, LayerDefinition {

    private final PessoaRepository repository;

    public PessoaServiceImpl(PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<PessoaDTO> findByFilter(PessoaFilter filter, Pageable pageable) {
        return repository
                .findByFilter(QueryUtil.aplicarLetraMaiusculaEColocarEntreCoringas(filter.getNome()), pageable)
                .map(PessoaDTO::new);
    }

    @Override
    public PessoaDTO create(PessoaDTO dto) {
        validarCamposObrigatorios(dto);
        Pessoa model = dto.toModel();
        model = repository.save(model);
        return new PessoaDTO(model);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<PessoaDTO> findAll() {
        return repository.findAll().stream().map(PessoaDTO::new).toList();
    }

    @Override
    public PessoaDTO findById(Long id) {
        return repository.findById(id).map(PessoaDTO::new).orElseThrow(() -> new ResourceNotFoundException("Nenhuma pessoa encontrada", this));
    }

    @Override
    public PessoaDTO update(PessoaDTO dto, Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Nenhuma pessoa encontrada", this);
        }
        validarCamposObrigatorios(dto);
        Pessoa model = dto.toModel();
        model = repository.save(model);
        return new PessoaDTO(model);
    }

    private void validarCamposObrigatorios(PessoaDTO pessoaDTO) {
        if (pessoaDTO.getNome() == null || pessoaDTO.getNome().isEmpty()) {
            throw new BusinessException("O nome da pessoa é obrigatório", this);
        }
        if (pessoaDTO.getDataNascimento() == null) {
            throw new BusinessException("A data de nascimento da pessoa é obrigatório", this);
        }
        if (pessoaDTO.getSexo() == null) {
            throw new BusinessException("O sexo da pessoa é obrigatório", this);
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
