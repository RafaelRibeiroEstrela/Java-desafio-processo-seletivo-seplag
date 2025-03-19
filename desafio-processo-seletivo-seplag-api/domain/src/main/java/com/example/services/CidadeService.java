package com.example.services;

import com.example.models.Cidade;
import com.example.models.filters.CidadeFilter;
import com.example.providers.data.CustomPage;
import com.example.providers.data.CustomPageable;
import com.example.providers.services.CrudService;


public interface CidadeService extends CrudService<Cidade, Long> {

    CustomPage<Cidade> findByFilter(CidadeFilter filter, CustomPageable pageable);
}
