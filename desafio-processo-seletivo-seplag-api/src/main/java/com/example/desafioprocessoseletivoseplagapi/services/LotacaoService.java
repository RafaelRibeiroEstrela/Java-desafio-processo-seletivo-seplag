package com.example.desafioprocessoseletivoseplagapi.services;

import com.example.desafioprocessoseletivoseplagapi.dtos.LotacaoDTO;
import com.example.desafioprocessoseletivoseplagapi.providers.services.CrudService;

import java.util.List;

public interface LotacaoService extends CrudService<LotacaoDTO, Long> {

    List<LotacaoDTO> findServidoresEfetivosByUnidadeId(long unidadeId);
}
