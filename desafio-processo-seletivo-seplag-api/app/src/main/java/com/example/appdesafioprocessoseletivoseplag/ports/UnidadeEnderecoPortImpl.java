package com.example.appdesafioprocessoseletivoseplag.ports;

import com.example.appdesafioprocessoseletivoseplag.entities.UnidadeEnderecoEntity;
import com.example.appdesafioprocessoseletivoseplag.repositories.UnidadeEnderecoRepository;
import com.example.models.UnidadeEndereco;
import com.example.ports.UnidadeEnderecoPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UnidadeEnderecoPortImpl implements UnidadeEnderecoPort {

    private final UnidadeEnderecoRepository repository;

    public UnidadeEnderecoPortImpl(UnidadeEnderecoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UnidadeEndereco> findByUnidadeId(long unidadeId) {
        List<UnidadeEnderecoEntity> entities = repository.findByUnidadeId(unidadeId);
        return entities.stream().map(obj -> new UnidadeEndereco(obj.getId().getUnidadeId(), obj.getId().getEnderecoId())).toList();
    }

    @Override
    public void deleteByUnidadeId(long unidadeId) {
        repository.deleteByUnidadeId(unidadeId);
    }

    @Override
    public void create(UnidadeEndereco unidadeEndereco) {
        UnidadeEnderecoEntity entity = new UnidadeEnderecoEntity(unidadeEndereco.getUnidadeId(), unidadeEndereco.getEnderecoId());
        repository.save(entity);
    }
}
