package com.example.desafioprocessoseletivoseplagapi.repositories;

import com.example.desafioprocessoseletivoseplagapi.models.Lotacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LotacaoRepository extends JpaRepository<Lotacao, Long> {

    @Query("SELECT l " +
            "FROM Lotacao l " +
            "JOIN ServidorEfetivo se ON (se.id = l.pessoaId) " +
            "WHERE l.unidadeId = :unidadeId ")
    List<Lotacao> findServidoresEfetivosByUnidadeId(Long unidadeId);

    @Query("SELECT l " +
            "FROM Lotacao l " +
            "JOIN Pessoa p ON (l.pessoaId = p.id) " +
            "WHERE (:nome IS NULL OR UPPER(p.nome) LIKE :nome) " +
            "AND (:unidadeId IS NULL OR l.unidadeId = :unidadeId) ")
    Page<Lotacao> findByFilter(String nome, Long unidadeId, Pageable pageable);

    List<Lotacao> findByPessoaId(Long pessoaId);
}
