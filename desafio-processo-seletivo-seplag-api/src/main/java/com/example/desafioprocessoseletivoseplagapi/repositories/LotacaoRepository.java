package com.example.desafioprocessoseletivoseplagapi.repositories;

import com.example.desafioprocessoseletivoseplagapi.models.Lotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LotacaoRepository extends JpaRepository<Lotacao, Long> {

    @Query("SELECT l " +
            "FROM Lotacao l " +
            "JOIN ServidorEfetivo se ON (se.id = l.pessoaId) " +
            "WHERE l.unidadeId = :unidadeId ")
    List<Lotacao> findServidoresEfetivosByUnidadeId(Long unidadeId);
}
