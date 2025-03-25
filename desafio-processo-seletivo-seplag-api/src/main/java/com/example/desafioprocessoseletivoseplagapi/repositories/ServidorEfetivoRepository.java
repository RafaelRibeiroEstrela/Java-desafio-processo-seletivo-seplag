package com.example.desafioprocessoseletivoseplagapi.repositories;

import com.example.desafioprocessoseletivoseplagapi.models.ServidorEfetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Long> {

    @Query("SELECT MAX(se.matricula) " +
            "FROM ServidorEfetivo se ")
    Long findMaxMatricula();
}
