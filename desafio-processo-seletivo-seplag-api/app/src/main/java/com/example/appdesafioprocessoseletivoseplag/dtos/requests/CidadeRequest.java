package com.example.appdesafioprocessoseletivoseplag.dtos.requests;

import com.example.models.enums.UfEnum;

public class CidadeRequest {

    private String nome;
    private UfEnum uf;

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
