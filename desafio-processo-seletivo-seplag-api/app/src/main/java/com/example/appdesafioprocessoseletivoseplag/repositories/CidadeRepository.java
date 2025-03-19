package com.example.appdesafioprocessoseletivoseplag.repositories;

import com.example.appdesafioprocessoseletivoseplag.entities.CidadeEntity;
import com.example.models.enums.UfEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CidadeRepository extends JpaRepository<CidadeEntity, Long> {

    @Query("SELECT c " +
            "FROM CidadeEntity c " +
            "WHERE (:nome IS NULL OR UPPER(c.nome) LIKE CONCAT('%',CONCAT(UPPER(:nome),'%'))) " +
            "AND (:uf IS NULL OR c.uf = :uf) ")
    Page<CidadeEntity> findByFilter(String nome, UfEnum uf, Pageable pageable);
}
