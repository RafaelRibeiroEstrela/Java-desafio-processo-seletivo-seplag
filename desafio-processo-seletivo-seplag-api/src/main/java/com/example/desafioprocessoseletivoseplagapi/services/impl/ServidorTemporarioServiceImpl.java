package com.example.desafioprocessoseletivoseplagapi.services.impl;

import com.example.desafioprocessoseletivoseplagapi.dtos.PessoaDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.ServidorTemporarioDTO;
import com.example.desafioprocessoseletivoseplagapi.models.ServidorTemporario;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.BusinessException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.LayerDefinition;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.ResourceNotFoundException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;
import com.example.desafioprocessoseletivoseplagapi.repositories.ServidorTemporarioRepository;
import com.example.desafioprocessoseletivoseplagapi.services.PessoaService;
import com.example.desafioprocessoseletivoseplagapi.services.ServidorTemporarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServidorTemporarioServiceImpl implements ServidorTemporarioService, LayerDefinition {

    private final ServidorTemporarioRepository repository;

    private final PessoaService pessoaService;

    public ServidorTemporarioServiceImpl(ServidorTemporarioRepository repository, PessoaService pessoaService) {
        this.repository = repository;
        this.pessoaService = pessoaService;
    }


    @Override
    public ServidorTemporarioDTO create(ServidorTemporarioDTO servidorTemporarioDTO) {
        validarCamposObrigatorios(servidorTemporarioDTO);
        PessoaDTO pessoaDTO = pessoaService.create(servidorTemporarioDTO.getPessoa());
        ServidorTemporario servidorTemporario = servidorTemporarioDTO.toModel();
        servidorTemporario.setId(pessoaDTO.getId());
        servidorTemporario.setDataAdmissao(LocalDate.now());
        servidorTemporario = repository.save(servidorTemporario);
        ServidorTemporarioDTO dto = new ServidorTemporarioDTO(servidorTemporario);
        dto.setPessoa(pessoaDTO);
        return dto;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
        pessoaService.delete(id);
    }

    @Override
    public Page<ServidorTemporarioDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(ServidorTemporarioDTO::new);
    }

    @Override
    public List<ServidorTemporarioDTO> findAll() {
        return repository.findAll().stream().map(ServidorTemporarioDTO::new).toList();
    }

    @Override
    public ServidorTemporarioDTO findById(Long id) {
        ServidorTemporario model = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum servidor temporario encontrado", this));
        PessoaDTO pessoaDTO = pessoaService.findById(id);
        ServidorTemporarioDTO dto = new ServidorTemporarioDTO(model);
        dto.setPessoa(pessoaDTO);
        return dto;
    }

    @Override
    public ServidorTemporarioDTO update(ServidorTemporarioDTO dto, Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Nenhum servidor efetivo encontrado", this);
        }
        pessoaService.update(dto.getPessoa(), id);
        ServidorTemporario model = dto.toModel();
        model.setId(id);
        model = repository.save(model);
        return new ServidorTemporarioDTO(model);
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_COMPONENT;
    }

    private void validarCamposObrigatorios(ServidorTemporarioDTO servidorTemporarioDTO) {
        if (servidorTemporarioDTO.getPessoa() == null) {
            throw new BusinessException("Os dados da pessoa são obrigatórios", this);
        }
    }


}
