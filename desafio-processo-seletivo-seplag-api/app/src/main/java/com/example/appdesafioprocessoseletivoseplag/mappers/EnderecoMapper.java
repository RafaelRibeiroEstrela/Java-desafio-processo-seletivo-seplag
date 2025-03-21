package com.example.appdesafioprocessoseletivoseplag.mappers;

import com.example.appdesafioprocessoseletivoseplag.entities.EnderecoEntity;
import com.example.endereco.EnderecoRequest;
import com.example.endereco.EnderecoResponse;
import com.example.models.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco requestToModel(EnderecoRequest request);
    Endereco entityToModel(EnderecoEntity entity);
    EnderecoEntity modelToEntity(Endereco model);
    EnderecoResponse modelToResponse(Endereco model);
}
