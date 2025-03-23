package com.example.appdesafioprocessoseletivoseplag.mappers;

import com.example.appdesafioprocessoseletivoseplag.entities.CidadeEntity;
import com.example.cidade.CidadeDTO;
import com.example.models.Cidade;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CidadeMapper {

    Cidade entityToModel(CidadeEntity entity);
    Cidade requestToModel(CidadeDTO request);
    CidadeEntity modelToEntity(Cidade model);
    CidadeDTO modelToResponse(Cidade model);
}
