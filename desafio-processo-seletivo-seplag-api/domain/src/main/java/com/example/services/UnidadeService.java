package com.example.services;

import com.example.models.Unidade;
import com.example.models.filters.UnidadeFilter;
import com.example.providers.data.CustomPage;
import com.example.providers.data.CustomPageable;
import com.example.providers.services.CrudService;

public interface UnidadeService extends CrudService<Unidade, Long> {

    CustomPage<Unidade> findByFilter(UnidadeFilter filter, CustomPageable pageable);
}
