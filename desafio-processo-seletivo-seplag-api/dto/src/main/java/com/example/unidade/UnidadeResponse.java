package com.example.unidade;

import com.example.endereco.EnderecoResponse;

import java.util.ArrayList;
import java.util.List;

public class UnidadeResponse {

    private Long id;
    private String nome;
    private String sigla;
    private List<EnderecoResponse> enderecos = new ArrayList<>();

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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<EnderecoResponse> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoResponse> enderecos) {
        this.enderecos = enderecos;
    }
}
