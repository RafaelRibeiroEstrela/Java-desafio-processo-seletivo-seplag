package com.example.desafioprocessoseletivoseplagapi.models.filters;

import com.example.desafioprocessoseletivoseplagapi.models.enums.UfEnum;

public class CidadeFilter {

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
