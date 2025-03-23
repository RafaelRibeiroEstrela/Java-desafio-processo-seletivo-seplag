package com.example.appdesafioprocessoseletivoseplag.repositories;

import com.example.appdesafioprocessoseletivoseplag.entities.UnidadeEnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnidadeEnderecoRepository extends JpaRepository<UnidadeEnderecoEntity, Long> {

    List<UnidadeEnderecoEntity> findByUnidadeId(Long unidadeId);

    void deleteByUnidadeId(Long unidadeId);
}
