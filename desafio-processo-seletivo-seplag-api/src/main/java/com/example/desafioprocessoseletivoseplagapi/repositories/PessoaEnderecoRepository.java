package com.example.desafioprocessoseletivoseplagapi.repositories;

import com.example.desafioprocessoseletivoseplagapi.models.PessoaEndereco;
import com.example.desafioprocessoseletivoseplagapi.models.ids.PessoaEnderecoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PessoaEnderecoRepository extends JpaRepository<PessoaEndereco, PessoaEnderecoId> {

    @Query("SELECT pe " +
            "FROM PessoaEndereco pe " +
            "WHERE pe.id.pessoaId = :pessoaId ")
    List<PessoaEndereco> findByPessoaId(Long pessoaId);

    @Modifying
    @Query("DELETE " +
            "FROM PessoaEndereco pe " +
            "WHERE pe.id.pessoaId = :pessoaId ")
    void deleteByPessoaId(Long pessoaId);
}
