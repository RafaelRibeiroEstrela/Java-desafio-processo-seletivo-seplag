package com.example.appdesafioprocessoseletivoseplag.mappers;

import com.example.appdesafioprocessoseletivoseplag.entities.UnidadeEnderecoEntity;
import com.example.models.UnidadeEndereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnidadeEnderecoMapper {

    UnidadeEndereco entityToModel(UnidadeEnderecoEntity entity);
    UnidadeEnderecoEntity modelToEntity(UnidadeEndereco model);
}
