package com.example.appdesafioprocessoseletivoseplag.ports;

import com.example.appdesafioprocessoseletivoseplag.mappers.CidadeMapper;
import com.example.appdesafioprocessoseletivoseplag.repositories.CidadeRepository;
import com.example.models.Cidade;
import com.example.models.filters.CidadeFilter;
import com.example.ports.CidadePort;
import com.example.providers.data.CustomPage;
import com.example.providers.data.CustomPageable;
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
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<Cidade> findAll() {
        return List.of();
    }

    @Override
    public Optional<Cidade> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Cidade save(Cidade cidade) {
        return null;
    }
}
