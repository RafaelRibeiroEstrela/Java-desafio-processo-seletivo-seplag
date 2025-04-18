package com.example.desafioprocessoseletivoseplagapi.services;

import com.example.desafioprocessoseletivoseplagapi.dtos.ServidorTemporarioDTO;
import com.example.desafioprocessoseletivoseplagapi.providers.services.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServidorTemporarioService extends CrudService<ServidorTemporarioDTO, Long> {

    Page<ServidorTemporarioDTO> findAll(Pageable pageable);
}
