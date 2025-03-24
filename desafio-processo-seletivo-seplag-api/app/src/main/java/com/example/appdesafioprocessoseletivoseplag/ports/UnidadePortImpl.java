package com.example.appdesafioprocessoseletivoseplag.ports;

import com.example.appdesafioprocessoseletivoseplag.entities.UnidadeEntity;
import com.example.appdesafioprocessoseletivoseplag.mappers.UnidadeMapper;
import com.example.appdesafioprocessoseletivoseplag.repositories.UnidadeRepository;
import com.example.appdesafioprocessoseletivoseplag.utils.PageUtil;
import com.example.appdesafioprocessoseletivoseplag.utils.QueryUtil;
import com.example.models.Unidade;
import com.example.models.filters.UnidadeFilter;
import com.example.ports.UnidadePort;
import com.example.providers.data.CustomPage;
import com.example.providers.data.CustomPageable;
import com.example.providers.data.impl.CustomPageImpl;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UnidadePortImpl implements UnidadePort {
    
    private final UnidadeRepository repository;
    private final UnidadeMapper mapper;

    public UnidadePortImpl(UnidadeRepository repository, UnidadeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CustomPage<Unidade> findByFilter(UnidadeFilter filter, CustomPageable pageable) {
        Page<UnidadeEntity> entities = repository.findByFilter(QueryUtil.aplicarLetraMaiusculaEColocarEntreCoringas(filter.getNome()), QueryUtil.aplicarLetraMaiusculaEColocarEntreCoringas(filter.getSigla()), PageUtil.toPageable(pageable));
        List<Unidade> models = entities.getContent().stream().map(mapper::entityToModel).toList();
        return new CustomPageImpl<>(models, entities.getTotalElements(), entities.getNumber(), entities.getSize());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Unidade> findAll() {
        List<UnidadeEntity> entities = repository.findAll();
        return entities.stream().map(mapper::entityToModel).toList();
    }

    @Override
    public Optional<Unidade> findById(Long id) {
       return repository.findById(id).map(mapper::entityToModel);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Unidade save(Unidade unidade) {
        UnidadeEntity entity = mapper.modelToEntity(unidade);
        entity = repository.save(entity);
        return mapper.entityToModel(entity);
    }
}
