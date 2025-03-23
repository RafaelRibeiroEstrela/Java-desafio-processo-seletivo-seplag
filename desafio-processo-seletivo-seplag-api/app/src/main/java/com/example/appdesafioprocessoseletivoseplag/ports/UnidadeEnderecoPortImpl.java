package com.example.appdesafioprocessoseletivoseplag.ports;

import com.example.appdesafioprocessoseletivoseplag.entities.UnidadeEnderecoEntity;
import com.example.appdesafioprocessoseletivoseplag.mappers.UnidadeEnderecoMapper;
import com.example.appdesafioprocessoseletivoseplag.repositories.UnidadeEnderecoRepository;
import com.example.models.UnidadeEndereco;
import com.example.ports.UnidadeEnderecoPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UnidadeEnderecoPortImpl implements UnidadeEnderecoPort {

    private final UnidadeEnderecoRepository repository;
    private final UnidadeEnderecoMapper mapper;

    public UnidadeEnderecoPortImpl(UnidadeEnderecoRepository repository, UnidadeEnderecoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<UnidadeEndereco> findByUnidadeId(long unidadeId) {
        List<UnidadeEnderecoEntity> entities = repository.findByUnidadeId(unidadeId);
        return entities.stream().map(mapper::entityToModel).toList();
    }

    @Override
    public void deleteByUnidadeId(long unidadeId) {
        repository.deleteByUnidadeId(unidadeId);
    }

    @Override
    public void create(UnidadeEndereco unidadeEndereco) {
        UnidadeEnderecoEntity entity = mapper.modelToEntity(unidadeEndereco);
        repository.save(entity);
    }
}
