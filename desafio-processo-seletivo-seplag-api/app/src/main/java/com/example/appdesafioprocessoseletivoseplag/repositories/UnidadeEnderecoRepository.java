package com.example.appdesafioprocessoseletivoseplag.repositories;

import com.example.appdesafioprocessoseletivoseplag.entities.UnidadeEnderecoEntity;
import com.example.appdesafioprocessoseletivoseplag.entities.ids.UnidadeEnderecoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnidadeEnderecoRepository extends JpaRepository<UnidadeEnderecoEntity, UnidadeEnderecoId> {

    @Query("SELECT ue " +
            "FROM UnidadeEnderecoEntity ue " +
            "WHERE ue.id.unidadeId = :unidadeId ")
    List<UnidadeEnderecoEntity> findByUnidadeId(Long unidadeId);
}
