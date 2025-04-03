package com.example.desafioprocessoseletivoseplagapi.services;

import com.example.desafioprocessoseletivoseplagapi.dtos.CidadeDTO;
import com.example.desafioprocessoseletivoseplagapi.providers.services.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CidadeService extends CrudService<CidadeDTO, Long> {

    Page<CidadeDTO> findAll(Pageable pageable);
}
