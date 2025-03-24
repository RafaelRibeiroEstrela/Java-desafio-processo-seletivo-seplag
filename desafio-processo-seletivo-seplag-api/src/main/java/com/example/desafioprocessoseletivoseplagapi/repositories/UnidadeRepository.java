package com.example.desafioprocessoseletivoseplagapi.repositories;

import com.example.desafioprocessoseletivoseplagapi.models.Unidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    @Query("SELECT u " +
            "FROM Unidade u " +
            "WHERE (:nome IS NULL OR UPPER(u.nome) LIKE :nome) " +
            "AND (:sigla IS NULL OR UPPER(u.sigla) LIKE :sigla) ")
    Page<Unidade> findByFilter(String nome, String sigla, Pageable pageable);
}
