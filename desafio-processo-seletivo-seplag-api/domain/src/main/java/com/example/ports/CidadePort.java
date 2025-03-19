package com.example.ports;

import com.example.models.Cidade;
import com.example.models.filters.CidadeFilter;
import com.example.providers.data.CustomPage;
import com.example.providers.data.CustomPageable;
import com.example.providers.ports.CrudPort;

public interface CidadePort extends CrudPort<Cidade, Long> {

    CustomPage<Cidade> findByFilter(CidadeFilter filter, CustomPageable pageable);
}
