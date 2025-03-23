package com.example.appdesafioprocessoseletivoseplag.mappers;

import com.example.appdesafioprocessoseletivoseplag.entities.UnidadeEntity;
import com.example.models.Unidade;
import com.example.unidade.UnidadeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnidadeMapper {

    Unidade requestToModel(UnidadeDTO request);
    Unidade entityToModel(UnidadeEntity entity);
    UnidadeDTO modelToResponse(Unidade model);
    UnidadeEntity modelToEntity(Unidade model);
}
