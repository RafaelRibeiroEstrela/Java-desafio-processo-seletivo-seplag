package com.example.appdesafioprocessoseletivoseplag.mappers;

import com.example.appdesafioprocessoseletivoseplag.entities.EnderecoEntity;
import com.example.endereco.EnderecoDTO;
import com.example.models.Endereco;
import org.mapstruct.Mapper;

public interface EnderecoMapper {

    Endereco requestToModel(EnderecoDTO request);
    Endereco entityToModel(EnderecoEntity entity);
    EnderecoEntity modelToEntity(Endereco model);
    EnderecoDTO modelToResponse(Endereco model);
}
