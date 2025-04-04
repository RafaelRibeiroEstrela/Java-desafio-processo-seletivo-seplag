package com.example.desafioprocessoseletivoseplagapi.services.impl;

import com.example.desafioprocessoseletivoseplagapi.dtos.*;
import com.example.desafioprocessoseletivoseplagapi.models.Lotacao;
import com.example.desafioprocessoseletivoseplagapi.models.ServidorEfetivo;
import com.example.desafioprocessoseletivoseplagapi.models.Unidade;
import com.example.desafioprocessoseletivoseplagapi.projecoes.ServidorEfetivoProjection;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.BusinessException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.LayerDefinition;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.ResourceNotFoundException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;
import com.example.desafioprocessoseletivoseplagapi.repositories.LotacaoRepository;
import com.example.desafioprocessoseletivoseplagapi.repositories.ServidorEfetivoRepository;
import com.example.desafioprocessoseletivoseplagapi.repositories.UnidadeRepository;
import com.example.desafioprocessoseletivoseplagapi.services.LotacaoService;
import com.example.desafioprocessoseletivoseplagapi.services.PessoaService;
import com.example.desafioprocessoseletivoseplagapi.services.ServidorEfetivoService;
import com.example.desafioprocessoseletivoseplagapi.services.UnidadeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServidorEfetivoServiceImpl implements ServidorEfetivoService, LayerDefinition {

    private final ServidorEfetivoRepository repository;
    private final PessoaService pessoaService;
    private final LotacaoRepository lotacaoRepository;
    private final UnidadeService unidadeService;

    public ServidorEfetivoServiceImpl(ServidorEfetivoRepository repository, PessoaService pessoaService, LotacaoRepository lotacaoRepository, UnidadeService unidadeService) {
        this.repository = repository;
        this.pessoaService = pessoaService;
        this.lotacaoRepository = lotacaoRepository;
        this.unidadeService = unidadeService;
    }


    @Override
    public ServidorEfetivoDTO create(ServidorEfetivoDTO dto) {
        validarCamposObrigatorios(dto);
        PessoaDTO pessoaDTO = pessoaService.create(dto.getPessoa());
        ServidorEfetivo model = dto.toModel();
        Long matricula = repository.findMaxMatricula();
        model.setMatricula(matricula == null ? 1000 : matricula + 1L);
        model.setId(pessoaDTO.getId());
        model = repository.save(model);
        return new ServidorEfetivoDTO(model);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
        pessoaService.delete(id);
    }

    @Override
    public Page<ServidorEfetivoDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(ServidorEfetivoDTO::new);
    }

    @Override
    public Page<ServidorEfetivoDTO> findByUnidadeId(long unidadeId, Pageable pageable) {
        Page<ServidorEfetivo> models = repository.findByUnidadeId(unidadeId, pageable);
        return models.map(obj -> {
            ServidorEfetivoDTO dto = new ServidorEfetivoDTO(obj);
            dto.setPessoa(pessoaService.findById(obj.getId()));
            return dto;
        });
    }

    @Override
    public Page<UnidadeDTO> findByNomeServidor(String nomeServidor, Pageable pageable) {
        if (nomeServidor != null && !nomeServidor.trim().isEmpty()) {
            nomeServidor = "%" + nomeServidor.toUpperCase() + "%";
        }
        Page<ServidorEfetivo> models = repository.findByNomeServidor(nomeServidor, pageable);
        return models.map(obj -> {
            Lotacao lotacao = lotacaoRepository.findByPessoaId(obj.getId()).getFirst();
            return unidadeService.findById(lotacao.getUnidadeId());
        });
    }

    @Override
    public List<ServidorEfetivoDTO> findAll() {
        return repository.findAll().stream().map(ServidorEfetivoDTO::new).toList();
    }

    @Override
    public ServidorEfetivoDTO findById(Long id) {
        ServidorEfetivo model = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum servidor efetivo encontrado", this));
        PessoaDTO pessoaDTO = pessoaService.findById(id);
        ServidorEfetivoDTO dto = new ServidorEfetivoDTO(model);
        dto.setPessoa(pessoaDTO);
        return dto;
    }

    @Override
    public ServidorEfetivoDTO update(ServidorEfetivoDTO dto, Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Nenhum servidor efetivo encontrado", this);
        }
        pessoaService.update(dto.getPessoa(), id);
        ServidorEfetivo model = dto.toModel();
        model.setId(id);
        model = repository.save(model);
        return new ServidorEfetivoDTO(model);
    }
    
    private void validarCamposObrigatorios(ServidorEfetivoDTO servidorEfetivoDTO) {
        if (servidorEfetivoDTO.getPessoa() == null) {
            throw new BusinessException("Os dados da pessoa são obrigatórios", this);
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
