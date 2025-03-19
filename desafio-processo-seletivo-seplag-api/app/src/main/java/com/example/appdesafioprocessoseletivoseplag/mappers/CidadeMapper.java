package com.example.appdesafioprocessoseletivoseplag.mappers;

import com.example.appdesafioprocessoseletivoseplag.dtos.requests.CidadeRequest;
import com.example.appdesafioprocessoseletivoseplag.dtos.responses.CidadeResponse;
import com.example.appdesafioprocessoseletivoseplag.entities.CidadeEntity;
import com.example.models.Cidade;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CidadeMapper {

    Cidade entityToModel(CidadeEntity entity);
    Cidade requestToModel(CidadeRequest request);
    CidadeEntity modelToEntity(Cidade model);
    CidadeResponse modelToResponse(Cidade model);
}
