package com.example.desafioprocessoseletivoseplagapi.dtos;

import com.example.desafioprocessoseletivoseplagapi.models.Cidade;
import com.example.desafioprocessoseletivoseplagapi.models.enums.UfEnum;
import com.example.desafioprocessoseletivoseplagapi.providers.dtos.ToModel;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CidadeDTO implements ToModel<Cidade> {

    private Long id;
    @NotEmpty(message = "O nome da cidade é obrigatório")
    private String nome;
    @NotNull(message = "A unidade federativa da cidade é obrigatório")
    private UfEnum uf;

    public CidadeDTO() {}

    public CidadeDTO(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
        this.uf = cidade.getUf();
    }

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

    @Override
    public Cidade toModel() {
        Cidade model = new Cidade();
        model.setId(id);
        model.setNome(nome);
        model.setUf(uf);
        return model;
    }
}
