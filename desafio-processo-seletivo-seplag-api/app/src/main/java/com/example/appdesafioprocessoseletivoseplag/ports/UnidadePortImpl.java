package com.example.appdesafioprocessoseletivoseplag.ports;

import com.example.appdesafioprocessoseletivoseplag.entities.UnidadeEnderecoEntity;
import com.example.appdesafioprocessoseletivoseplag.entities.UnidadeEntity;
import com.example.appdesafioprocessoseletivoseplag.entities.ids.UnidadeEnderecoId;
import com.example.appdesafioprocessoseletivoseplag.mappers.UnidadeMapper;
import com.example.appdesafioprocessoseletivoseplag.repositories.UnidadeEnderecoRepository;
import com.example.appdesafioprocessoseletivoseplag.repositories.UnidadeRepository;
import com.example.appdesafioprocessoseletivoseplag.utils.PageUtil;
import com.example.models.Endereco;
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

    private final UnidadeEnderecoRepository unidadeEnderecoRepository;

    public UnidadePortImpl(UnidadeRepository repository, UnidadeMapper mapper, UnidadeEnderecoRepository unidadeEnderecoRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.unidadeEnderecoRepository = unidadeEnderecoRepository;
    }


    @Override
    public CustomPage<Unidade> findByFilter(UnidadeFilter filter, CustomPageable pageable) {
        Page<UnidadeEntity> entities = repository.findByFilter(filter.getNome(), filter.getSigla(), PageUtil.toPageable(pageable));
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
        Unidade unidade = repository.findById(id).map(mapper::entityToModel).orElse(null);
        if (unidade == null) {
            return Optional.empty();
        }
        List<UnidadeEnderecoEntity> unidadeEnderecoEntities = unidadeEnderecoRepository.findByUnidadeId(id);
        unidade.setEnderecos(unidadeEnderecoEntities.stream().map(this::montarRelacionamentoEndereco).toList());
        return Optional.of(unidade);
    }

    private Endereco montarRelacionamentoEndereco(UnidadeEnderecoEntity unidadeEnderecoEntity) {
        Endereco endereco = new Endereco();
        endereco.setId(unidadeEnderecoEntity.getId().getEnderecoId());
        return endereco;
    }

    private void salvarRelacionamentoEndereco(long unidadeId, List<Endereco> enderecos) {
        for (Endereco endereco : enderecos) {
            UnidadeEnderecoEntity unidadeEnderecoEntity = new UnidadeEnderecoEntity();
            unidadeEnderecoEntity.setId(new UnidadeEnderecoId(unidadeId, endereco.getId()));
            unidadeEnderecoRepository.save(unidadeEnderecoEntity);
        }
    }

    @Override
    public Unidade save(Unidade unidade) {
        UnidadeEntity entity = mapper.modelToEntity(unidade);
        entity = repository.save(entity);
        salvarRelacionamentoEndereco(entity.getId(), unidade.getEnderecos());
        return mapper.entityToModel(entity);
    }
}
