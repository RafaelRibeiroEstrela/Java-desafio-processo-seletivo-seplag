package com.example.desafioprocessoseletivoseplagapi.services.impl;

import com.example.desafioprocessoseletivoseplagapi.dtos.FotoDTO;
import com.example.desafioprocessoseletivoseplagapi.models.Foto;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.BusinessException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.LayerDefinition;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.ResourceNotFoundException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.StorageException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;
import com.example.desafioprocessoseletivoseplagapi.repositories.FotoRepository;
import com.example.desafioprocessoseletivoseplagapi.services.FotoService;
import com.example.desafioprocessoseletivoseplagapi.services.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FotoServiceImpl implements FotoService, LayerDefinition {

    private final FotoRepository repository;
    private final StorageService storageService;

    @Value("${s3.bucket-name}")
    private String bucketName;

    @Value("${server.port}")
    private String applicationPort;

    public FotoServiceImpl(FotoRepository repository, StorageService storageService) {
        this.repository = repository;
        this.storageService = storageService;
    }

    @Override
    public FotoDTO upload(FotoDTO dto, long pessoaId) {
        validarCamposObrigatorios(dto);
        String key = UUID.randomUUID().toString();
        storageService.upload(dto, bucketName, key);
        Foto model = dto.toModel();
        model.setBucket(bucketName);
        model.setData(LocalDateTime.now());
        model.setKey(key);
        model.setUrl(gerarUrl(model.getData(), key));
        model.setPessoaId(pessoaId);
        model = repository.save(model);
        FotoDTO fotoDTO = new FotoDTO();
        fotoDTO.setId(model.getId());
        fotoDTO.setUrl(model.getUrl());
        fotoDTO.setData(model.getData());
        return fotoDTO;
    }

    @Override
    public List<FotoDTO> download(long pessoaId) {
        return repository.findByPessoaId(pessoaId).stream().map(foto -> {
            try {
                return storageService.download(bucketName, foto.getKey());
            } catch (IOException e) {
                throw new StorageException("Erro ao recuperar arquivo: " + e.getMessage(), this);
            }
        }).toList();
    }

    @Override
    public FotoDTO download(String key) {
        Foto model = repository.findByKey(key).orElseThrow(() -> new ResourceNotFoundException("Nenhuma foto encontrada", this));
        if (LocalDateTime.now().minusMinutes(5L).isAfter(model.getData())) {
            throw new BusinessException("O link expirou", this);
        }
        try {
            return storageService.download(bucketName, model.getKey());
        } catch (IOException e) {
            throw new StorageException("Erro ao recuperar arquivo: " + e.getMessage(), this);
        }
    }

    @Override
    public List<FotoDTO> findByPessoaId(long pessoaId) {
        return repository.findByPessoaId(pessoaId).stream().map(FotoDTO::new).toList();
    }

    @Override
    public void delete(long pessoaId) {
        List<Foto> models = repository.findByPessoaId(pessoaId);
        models.forEach(model -> {
            storageService.delete(bucketName, model.getKey());
            repository.deleteById(model.getId());
        });
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_COMPONENT;
    }

    private void validarCamposObrigatorios(FotoDTO fotoDTO) {
        if (fotoDTO.getContent() == null) {
            throw new BusinessException("O conteudo da foto é obrigatório", this);
        }
    }

    private String gerarUrl(LocalDateTime dataCriacao, String key) {
        LocalDateTime agora = LocalDateTime.now();
        if (agora.minusMinutes(5L).isAfter(dataCriacao)) {
            return null;
        }
        return "http://localhost:" + applicationPort + "/" + bucketName + "/" + key;
    }
}
