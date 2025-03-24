package com.example.appdesafioprocessoseletivoseplag.mappers.impl;

import com.example.appdesafioprocessoseletivoseplag.entities.EnderecoEntity;
import com.example.appdesafioprocessoseletivoseplag.mappers.CidadeMapper;
import com.example.appdesafioprocessoseletivoseplag.mappers.EnderecoMapper;
import com.example.endereco.EnderecoDTO;
import com.example.models.Cidade;
import com.example.models.Endereco;
import com.example.models.enums.TipoLogradouroEnum;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapperImpl implements EnderecoMapper {

    private final CidadeMapper cidadeMapper;

    public EnderecoMapperImpl(CidadeMapper cidadeMapper) {
        this.cidadeMapper = cidadeMapper;
    }

    @Override
    public Endereco requestToModel(EnderecoDTO request) {
        Endereco model = new Endereco();
        model.setId(request.getId());
        model.setTipoLogradouro(request.getTipoLogradouro());
        model.setNumero(request.getNumero());
        model.setBairro(request.getBairro());
        model.setLogradouro(request.getLogradouro());
        model.setCidade(cidadeMapper.requestToModel(request.getCidade()));
        return model;
    }

    @Override
    public Endereco entityToModel(EnderecoEntity entity) {
        Endereco model = new Endereco();
        model.setId(entity.getId());
        model.setTipoLogradouro(entity.getTipoLogradouro() == null ? null : entity.getTipoLogradouro().name());
        model.setNumero(entity.getNumero());
        model.setLogradouro(entity.getLogradouro());
        model.setBairro(entity.getBairro());
        Cidade cidade = new Cidade();
        cidade.setId(entity.getCidadeId());
        model.setCidade(cidade);
        return model;
    }

    @Override
    public EnderecoEntity modelToEntity(Endereco model) {
        EnderecoEntity entity = new EnderecoEntity();
        entity.setId(model.getId());
        entity.setBairro(model.getBairro());
        entity.setNumero(model.getNumero());
        entity.setLogradouro(model.getLogradouro());
        entity.setTipoLogradouro(model.getTipoLogradouro() == null || model.getTipoLogradouro().isEmpty() ? null : TipoLogradouroEnum.toEnum(model.getTipoLogradouro()));
        entity.setCidadeId(model.getCidade() == null ? null : model.getCidade().getId());
        return entity;
    }

    @Override
    public EnderecoDTO modelToResponse(Endereco model) {
        EnderecoDTO response = new EnderecoDTO();
        response.setId(model.getId());
        response.setTipoLogradouro(model.getTipoLogradouro());
        response.setNumero(model.getNumero());
        response.setBairro(model.getBairro());
        response.setLogradouro(model.getLogradouro());
        response.setCidade(cidadeMapper.modelToResponse(model.getCidade()));
        return response;
    }
}
