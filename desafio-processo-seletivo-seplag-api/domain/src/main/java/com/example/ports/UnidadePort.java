package com.example.ports;

import com.example.models.Unidade;
import com.example.models.filters.UnidadeFilter;
import com.example.providers.data.CustomPage;
import com.example.providers.data.CustomPageable;
import com.example.providers.ports.CrudPort;

public interface UnidadePort extends CrudPort<Unidade, Long> {

    CustomPage<Unidade> findByFilter(UnidadeFilter filter, CustomPageable pageable);
}
