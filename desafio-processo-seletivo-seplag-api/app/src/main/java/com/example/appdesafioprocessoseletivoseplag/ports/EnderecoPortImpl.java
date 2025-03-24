package com.example.appdesafioprocessoseletivoseplag.ports;

import com.example.appdesafioprocessoseletivoseplag.entities.EnderecoEntity;
import com.example.appdesafioprocessoseletivoseplag.mappers.EnderecoMapper;
import com.example.appdesafioprocessoseletivoseplag.repositories.EnderecoRepository;
import com.example.models.Endereco;
import com.example.ports.EnderecoPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class EnderecoPortImpl implements EnderecoPort {
    
    private final EnderecoRepository repository;
    private final EnderecoMapper mapper;

    public EnderecoPortImpl(EnderecoRepository repository, EnderecoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Endereco> findAll() {
        List<EnderecoEntity> entities = repository.findAll();
        return entities.stream().map(mapper::entityToModel).toList();
    }

    @Override
    public Optional<Endereco> findById(Long id) {
        return repository.findById(id).map(mapper::entityToModel);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Endereco save(Endereco endereco) {
        EnderecoEntity entity = mapper.modelToEntity(endereco);
        entity = repository.save(entity);
        return mapper.entityToModel(entity);
    }
}
