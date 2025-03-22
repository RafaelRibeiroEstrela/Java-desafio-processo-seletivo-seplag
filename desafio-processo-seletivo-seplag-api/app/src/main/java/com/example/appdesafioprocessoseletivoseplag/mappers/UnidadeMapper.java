package com.example.appdesafioprocessoseletivoseplag.mappers;

import com.example.appdesafioprocessoseletivoseplag.entities.UnidadeEntity;
import com.example.models.Unidade;
import com.example.unidade.UnidadeRequest;
import com.example.unidade.UnidadeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnidadeMapper {

    Unidade requestToModel(UnidadeRequest request);
    Unidade entityToModel(UnidadeEntity entity);
    UnidadeResponse modelToResponse(Unidade model);
    UnidadeEntity modelToEntity(Unidade model);
}
