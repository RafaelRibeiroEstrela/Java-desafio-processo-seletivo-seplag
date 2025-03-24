package com.example.appdesafioprocessoseletivoseplag.ports;

import com.example.appdesafioprocessoseletivoseplag.entities.CidadeEntity;
import com.example.appdesafioprocessoseletivoseplag.mappers.CidadeMapper;
import com.example.appdesafioprocessoseletivoseplag.repositories.CidadeRepository;
import com.example.appdesafioprocessoseletivoseplag.utils.PageUtil;
import com.example.appdesafioprocessoseletivoseplag.utils.QueryUtil;
import com.example.models.Cidade;
import com.example.models.filters.CidadeFilter;
import com.example.ports.CidadePort;
import com.example.providers.data.CustomPage;
import com.example.providers.data.CustomPageable;
import com.example.providers.data.impl.CustomPageImpl;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CidadePortImpl implements CidadePort {

    private final CidadeRepository repository;
    private final CidadeMapper mapper;

    public CidadePortImpl(CidadeRepository repository, CidadeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CustomPage<Cidade> findByFilter(CidadeFilter filter, CustomPageable pageable) {
        Page<CidadeEntity> entities = repository.findByFilter(QueryUtil.aplicarLetraMaiusculaEColocarEntreCoringas(filter.getNome()), filter.getUf(), PageUtil.toPageable(pageable));
        List<Cidade> models = entities.stream().map(mapper::entityToModel).toList();
        return new CustomPageImpl<>(models, entities.getTotalElements(), entities.getNumber(), entities.getSize());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Cidade> findAll() {
        List<CidadeEntity> entities = repository.findAll();
        return entities.stream().map(mapper::entityToModel).toList();
    }

    @Override
    public Optional<Cidade> findById(Long id) {
        return repository.findById(id).map(mapper::entityToModel);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Cidade save(Cidade cidade) {
        CidadeEntity entity = mapper.modelToEntity(cidade);
        entity = repository.save(entity);
        return mapper.entityToModel(entity);
    }
}
