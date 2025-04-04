package com.example.desafioprocessoseletivoseplagapi.dtos;

public class CustomServidorEfetivoDTO {

    private String nome;
    private Integer idade;
    private String unidade;
    private String fotografia;

    public CustomServidorEfetivoDTO() {}

    public CustomServidorEfetivoDTO(String nome, Integer idade, String unidade, String fotografia) {
        this.nome = nome;
        this.idade = idade;
        this.unidade = unidade;
        this.fotografia = fotografia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }
}
