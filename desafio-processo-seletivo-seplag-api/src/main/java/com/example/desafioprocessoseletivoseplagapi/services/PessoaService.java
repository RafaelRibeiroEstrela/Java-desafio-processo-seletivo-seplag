package com.example.desafioprocessoseletivoseplagapi.services;

import com.example.desafioprocessoseletivoseplagapi.dtos.PessoaDTO;
import com.example.desafioprocessoseletivoseplagapi.models.filters.PessoaFilter;
import com.example.desafioprocessoseletivoseplagapi.providers.services.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaService extends CrudService<PessoaDTO, Long> {

    Page<PessoaDTO> findByFilter(PessoaFilter filter, Pageable pageable);
}
