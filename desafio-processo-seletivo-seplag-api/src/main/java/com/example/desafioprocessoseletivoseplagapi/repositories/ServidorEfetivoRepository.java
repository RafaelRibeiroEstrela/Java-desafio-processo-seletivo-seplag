package com.example.desafioprocessoseletivoseplagapi.repositories;

import com.example.desafioprocessoseletivoseplagapi.models.ServidorEfetivo;
import com.example.desafioprocessoseletivoseplagapi.projecoes.ServidorEfetivoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Long> {

    @Query("SELECT MAX(se.matricula) " +
            "FROM ServidorEfetivo se ")
    Long findMaxMatricula();

    @Query("SELECT s " +
            "FROM Lotacao l " +
            "LEFT JOIN ServidorEfetivo s ON (s.id = l.pessoaId) " +
            "LEFT JOIN Pessoa p ON (p.id = s.id) " +
            "LEFT JOIN Unidade u ON (u.id = l.unidadeId) " +
            "LEFT JOIN Foto f ON (f.pessoaId = s.id) " +
            "WHERE (u.id = :unidadeId) ")
    Page<ServidorEfetivo> findByUnidadeId(Long unidadeId, Pageable pageable);

    @Query("SELECT s " +
            "FROM ServidorEfetivo s " +
            "JOIN Pessoa p ON (p.id = s.id) " +
            "WHERE (:nomeServidor IS NULL OR UPPER(p.nome) LIKE :nomeServidor) ")
    Page<ServidorEfetivo> findByNomeServidor(String nomeServidor, Pageable pageable);
}
