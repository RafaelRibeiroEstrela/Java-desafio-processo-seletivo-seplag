package com.example.desafioprocessoseletivoseplagapi.services;

import com.example.desafioprocessoseletivoseplagapi.dtos.FotoDTO;

import java.util.List;

public interface FotoService {

    FotoDTO upload(FotoDTO dto, long pessoaId);
    List<FotoDTO> download(long pessoaId);
    FotoDTO download(String key);
    List<FotoDTO> findByPessoaId(long pessoaId);
    void delete(long pessoaId);
}

