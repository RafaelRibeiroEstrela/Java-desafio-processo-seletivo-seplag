package com.example.unidade;

import com.example.endereco.EnderecoRequest;

import java.util.ArrayList;
import java.util.List;

public class UnidadeRequest {

    private String nome;
    private String sigla;
    private List<EnderecoRequest> enderecos = new ArrayList<>();

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

    public List<EnderecoRequest> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoRequest> enderecos) {
        this.enderecos = enderecos;
    }
}
