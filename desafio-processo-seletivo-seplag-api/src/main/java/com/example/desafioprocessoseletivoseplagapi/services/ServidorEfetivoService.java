package com.example.desafioprocessoseletivoseplagapi.services;

import com.example.desafioprocessoseletivoseplagapi.dtos.ServidorEfetivoDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.UnidadeDTO;
import com.example.desafioprocessoseletivoseplagapi.providers.services.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServidorEfetivoService extends CrudService<ServidorEfetivoDTO, Long> {

    Page<ServidorEfetivoDTO> findAll(Pageable pageable);
    Page<ServidorEfetivoDTO> findByUnidadeId(long unidadeId, Pageable pageable);
    Page<UnidadeDTO> findByNomeServidor(String nomeServidor, Pageable pageable);
}
