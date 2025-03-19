package com.example.appdesafioprocessoseletivoseplag.dtos.responses;

import com.example.models.enums.UfEnum;

public class CidadeResponse {

    private Long id;
    private String nome;
    private UfEnum uf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UfEnum getUf() {
        return uf;
    }

    public void setUf(UfEnum uf) {
        this.uf = uf;
    }
}
