package com.example.desafioprocessoseletivoseplagapi.services.impl;

import com.example.desafioprocessoseletivoseplagapi.dtos.LotacaoDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.PessoaDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.UnidadeDTO;
import com.example.desafioprocessoseletivoseplagapi.models.Lotacao;
import com.example.desafioprocessoseletivoseplagapi.models.filters.LotacaoFilter;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.BusinessException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.LayerDefinition;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.ResourceNotFoundException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;
import com.example.desafioprocessoseletivoseplagapi.repositories.LotacaoRepository;
import com.example.desafioprocessoseletivoseplagapi.services.LotacaoService;
import com.example.desafioprocessoseletivoseplagapi.services.PessoaService;
import com.example.desafioprocessoseletivoseplagapi.services.UnidadeService;
import com.example.desafioprocessoseletivoseplagapi.utils.QueryUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LotacaoServiceImpl implements LotacaoService, LayerDefinition {

    private final LotacaoRepository repository;

    private final UnidadeService unidadeService;
    private final PessoaService pessoaService;

    public LotacaoServiceImpl(LotacaoRepository repository, UnidadeService unidadeService, PessoaService pessoaService) {
        this.repository = repository;
        this.unidadeService = unidadeService;
        this.pessoaService = pessoaService;
    }

    @Override
    public LotacaoDTO create(LotacaoDTO dto) {
        validarCamposObrigatorios(dto);
        Lotacao lotacao = dto.toModel();
        lotacao.setDataLotacao(LocalDate.now());
        lotacao.setPessoaId(dto.getPessoa().getId());
        lotacao.setUnidadeId(dto.getUnidade().getId());
        lotacao = repository.save(lotacao);
        return new LotacaoDTO(lotacao);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<LotacaoDTO> findAll() {
        return repository.findAll().stream().map(LotacaoDTO::new).toList();
    }

    @Override
    public LotacaoDTO findById(Long id) {
        Lotacao lotacao = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhuma lotacao encontrada", this));
        PessoaDTO pessoaDTO = pessoaService.findById(lotacao.getPessoaId());
        UnidadeDTO unidadeDTO = unidadeService.findById(lotacao.getUnidadeId());
        LotacaoDTO dto = new LotacaoDTO(lotacao);
        dto.setPessoa(pessoaDTO);
        dto.setUnidade(unidadeDTO);
        return dto;
    }

    @Override
    public LotacaoDTO update(LotacaoDTO dto, Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Nenhuma lotacao encontrada", this);
        }
        validarCamposObrigatorios(dto);
        Lotacao lotacao = dto.toModel();
        lotacao.setId(id);
        lotacao.setPessoaId(dto.getPessoa().getId());
        lotacao.setUnidadeId(dto.getUnidade().getId());
        lotacao = repository.save(lotacao);
        return new LotacaoDTO(lotacao);
    }

    @Override
    public Page<LotacaoDTO> findByFilter(LotacaoFilter filter, Pageable pageable) {
        Page<Lotacao> models = repository.findByFilter(QueryUtil.aplicarLetraMaiusculaEColocarEntreCoringas(filter.getNomePessoa()), filter.getUnidadeId(), pageable);
        return models.map(lotacao -> {
            LotacaoDTO dto = new LotacaoDTO(lotacao);
            dto.setPessoa(pessoaService.findById(lotacao.getPessoaId()));
            dto.setUnidade(unidadeService.findById(lotacao.getUnidadeId()));
            return dto;
        });
    }


    private void validarCamposObrigatorios(LotacaoDTO lotacaoDTO) {
        if (lotacaoDTO.getPessoa() == null || lotacaoDTO.getPessoa().getId() == null) {
            throw new BusinessException("A pessoa é obrigatório", this);
        }
        if (lotacaoDTO.getUnidade() == null || lotacaoDTO.getUnidade().getId() == null) {
            throw new BusinessException("A unidade é obrigatório", this);
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
