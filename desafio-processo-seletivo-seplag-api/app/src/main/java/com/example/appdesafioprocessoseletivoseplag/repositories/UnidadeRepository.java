package com.example.appdesafioprocessoseletivoseplag.repositories;

import com.example.appdesafioprocessoseletivoseplag.entities.UnidadeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UnidadeRepository extends JpaRepository<UnidadeEntity, Long> {

    @Query("SELECT u " +
            "FROM UnidadeEntity u " +
            "WHERE (:nome IS NULL OR UPPER(u.nome) LIKE CONCAT('%',CONCAT(UPPER(:nome),'%'))) " +
            "AND (:sigla IS NULL OR UPPER(u.sigla) LIKE CONCAT('%',CONCAT(UPPER(:sigla),'%'))) ")
    Page<UnidadeEntity> findByFilter(String nome, String sigla, Pageable pageable);
}
