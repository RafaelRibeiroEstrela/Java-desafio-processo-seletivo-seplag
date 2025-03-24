package com.example.desafioprocessoseletivoseplagapi.repositories;

import com.example.desafioprocessoseletivoseplagapi.models.UnidadeEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnidadeEnderecoRepository extends JpaRepository<UnidadeEndereco, Long> {


    @Query("SELECT ue " +
            "FROM UnidadeEndereco ue " +
            "WHERE ue.id.unidadeId = :unidadeId ")
    List<UnidadeEndereco> findByUnidadeId(Long unidadeId);

    @Modifying
    @Query("DELETE " +
            "FROM UnidadeEndereco ue " +
            "WHERE ue.id.unidadeId = :unidadeId ")
    void deleteByUnidadeId(Long unidadeId);
}
