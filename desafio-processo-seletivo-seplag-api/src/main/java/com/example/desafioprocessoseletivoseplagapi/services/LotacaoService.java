package com.example.desafioprocessoseletivoseplagapi.services;

import com.example.desafioprocessoseletivoseplagapi.dtos.LotacaoDTO;
import com.example.desafioprocessoseletivoseplagapi.models.filters.LotacaoFilter;
import com.example.desafioprocessoseletivoseplagapi.providers.services.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LotacaoService extends CrudService<LotacaoDTO, Long> {

    Page<LotacaoDTO> findByFilter(LotacaoFilter filter, Pageable pageable);
}
