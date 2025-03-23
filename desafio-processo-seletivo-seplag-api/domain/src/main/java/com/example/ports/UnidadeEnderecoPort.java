package com.example.ports;

import com.example.models.UnidadeEndereco;

import java.util.List;

public interface UnidadeEnderecoPort {

    List<UnidadeEndereco> findByUnidadeId(long unidadeId);
    void deleteByUnidadeId(long unidadeId);
    void create(UnidadeEndereco unidadeEndereco);
}
