package com.example.models;

import com.example.models.enums.UfEnum;
import com.example.providers.models.BaseModel;

public class Cidade implements BaseModel<Long> {

    private Long id;
    private String nome;
    private UfEnum uf;

    @Override
    public Long getId() {
        return id;
    }

    @Override
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
