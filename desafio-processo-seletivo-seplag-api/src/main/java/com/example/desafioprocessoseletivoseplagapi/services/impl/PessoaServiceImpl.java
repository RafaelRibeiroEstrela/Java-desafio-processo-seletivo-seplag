package com.example.desafioprocessoseletivoseplagapi.services.impl;

import com.example.desafioprocessoseletivoseplagapi.dtos.EnderecoDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.PessoaDTO;
import com.example.desafioprocessoseletivoseplagapi.models.Pessoa;
import com.example.desafioprocessoseletivoseplagapi.models.PessoaEndereco;
import com.example.desafioprocessoseletivoseplagapi.models.filters.PessoaFilter;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.BusinessException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.LayerDefinition;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.ResourceNotFoundException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;
import com.example.desafioprocessoseletivoseplagapi.repositories.PessoaEnderecoRepository;
import com.example.desafioprocessoseletivoseplagapi.repositories.PessoaRepository;
import com.example.desafioprocessoseletivoseplagapi.services.EnderecoService;
import com.example.desafioprocessoseletivoseplagapi.services.PessoaService;
import com.example.desafioprocessoseletivoseplagapi.utils.QueryUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService, LayerDefinition {

    private final PessoaRepository repository;

    private final PessoaEnderecoRepository pessoaEnderecoRepository;
    private final EnderecoService enderecoService;

    public PessoaServiceImpl(PessoaRepository repository, PessoaEnderecoRepository pessoaEnderecoRepository, EnderecoService enderecoService) {
        this.repository = repository;
        this.pessoaEnderecoRepository = pessoaEnderecoRepository;
        this.enderecoService = enderecoService;
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
        List<EnderecoDTO> enderecoDTOList = dto.getEnderecos().stream().map(enderecoService::create).toList();
        Pessoa model = dto.toModel();
        model = repository.save(model);
        for (EnderecoDTO enderecoDTO : enderecoDTOList) {
            pessoaEnderecoRepository.save(new PessoaEndereco(model.getId(), enderecoDTO.getId()));
        }
        return new PessoaDTO(model);
    }

    @Override
    public void delete(Long id) {
        List<PessoaEndereco> pessoaEnderecoList = pessoaEnderecoRepository.findByPessoaId(id);
        pessoaEnderecoRepository.deleteByPessoaId(id);
        pessoaEnderecoList.forEach(pessoaEndereco -> enderecoService.delete(pessoaEndereco.getId().getEnderecoId()));
        repository.deleteById(id);
    }

    @Override
    public List<PessoaDTO> findAll() {
        return repository.findAll().stream().map(PessoaDTO::new).toList();
    }

    @Override
    public PessoaDTO findById(Long id) {
        Pessoa model = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhuma pessoa encontrada", this));
        List<PessoaEndereco> pessoaEnderecoList = pessoaEnderecoRepository.findByPessoaId(id);
        List<EnderecoDTO> enderecoDTOList = pessoaEnderecoList.stream().map(pessoaEndereco -> enderecoService.findById(pessoaEndereco.getId().getEnderecoId())).toList();
        PessoaDTO dto = new PessoaDTO(model);
        dto.setEnderecos(enderecoDTOList);
        return dto;
    }

    @Override
    public PessoaDTO update(PessoaDTO dto, Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Nenhuma unidade encontrada", this);
        }
        validarCamposObrigatorios(dto);
        atualizarEndereco(dto, id);
        Pessoa model = dto.toModel();
        model.setId(id);
        model = repository.save(model);
        return new PessoaDTO(model);
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_COMPONENT;
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
        if (pessoaDTO.getEnderecos().isEmpty()) {
            throw new BusinessException("O endereco da pessoa é obrigatório", this);
        }
    }

    private void atualizarEndereco(PessoaDTO dto, long id) {
        List<PessoaEndereco> pessoaEnderecoList = pessoaEnderecoRepository.findByPessoaId(id);
        pessoaEnderecoRepository.deleteByPessoaId(id);
        pessoaEnderecoList.forEach(pessoaEndereco -> enderecoService.delete(pessoaEndereco.getId().getEnderecoId()));
        List<EnderecoDTO> enderecoDTOList = dto.getEnderecos().stream().map(enderecoService::create).toList();
        enderecoDTOList.forEach(enderecoDTO -> pessoaEnderecoRepository.save(new PessoaEndereco(id, enderecoDTO.getId())));
    }

}
