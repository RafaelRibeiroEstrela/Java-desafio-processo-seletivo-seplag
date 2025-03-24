package com.example.appdesafioprocessoseletivoseplag.mappers.impl;

import com.example.appdesafioprocessoseletivoseplag.entities.UnidadeEntity;
import com.example.appdesafioprocessoseletivoseplag.mappers.EnderecoMapper;
import com.example.appdesafioprocessoseletivoseplag.mappers.UnidadeMapper;
import com.example.models.Unidade;
import com.example.unidade.UnidadeDTO;
import org.springframework.stereotype.Component;

@Component
public class UnidadeMapperImpl implements UnidadeMapper {

    private final EnderecoMapper enderecoMapper;

    public UnidadeMapperImpl(EnderecoMapper enderecoMapper) {
        this.enderecoMapper = enderecoMapper;
    }

    @Override
    public Unidade requestToModel(UnidadeDTO request) {
        Unidade unidade = new Unidade();
        unidade.setId(request.getId());
        unidade.setNome(request.getNome());
        unidade.setSigla(request.getSigla());
        unidade.setEnderecos(request.getEnderecos().stream().map(enderecoMapper::requestToModel).toList());
        return unidade;
    }

    @Override
    public Unidade entityToModel(UnidadeEntity entity) {
        Unidade model = new Unidade();
        model.setId(entity.getId());
        model.setNome(entity.getNome());
        model.setSigla(entity.getSigla());
        return model;
    }

    @Override
    public UnidadeDTO modelToResponse(Unidade model) {
        UnidadeDTO response = new UnidadeDTO();
        response.setId(model.getId());
        response.setNome(model.getNome());
        response.setSigla(model.getSigla());
        response.setEnderecos(model.getEnderecos().stream().map(enderecoMapper::modelToResponse).toList());
        return response;
    }

    @Override
    public UnidadeEntity modelToEntity(Unidade model) {
        UnidadeEntity entity = new UnidadeEntity();
        entity.setId(model.getId());
        entity.setNome(model.getNome());
        entity.setSigla(model.getSigla());
        return entity;
    }
}
