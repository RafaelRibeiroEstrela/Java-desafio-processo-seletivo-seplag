package com.example.appdesafioprocessoseletivoseplag.repositories;

import com.example.appdesafioprocessoseletivoseplag.entities.UnidadeEnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnidadeEnderecoRepository extends JpaRepository<UnidadeEnderecoEntity, Long> {


    @Query("SELECT ue " +
            "FROM UnidadeEnderecoEntity ue " +
            "WHERE ue.id.unidadeId = :unidadeId ")
    List<UnidadeEnderecoEntity> findByUnidadeId(Long unidadeId);

    @Modifying
    @Query("DELETE " +
            "FROM UnidadeEnderecoEntity ue " +
            "WHERE ue.id.unidadeId = :unidadeId ")
    void deleteByUnidadeId(Long unidadeId);
}
