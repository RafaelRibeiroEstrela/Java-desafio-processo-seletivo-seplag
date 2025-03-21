package com.example.endereco;

import com.example.cidade.CidadeRequest;

public class EnderecoRequest {

    private String tipoLogradouro;
    private String logradouro;
    private String numero;
    private String bairro;
    private CidadeRequest cidade;

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public CidadeRequest getCidade() {
        return cidade;
    }

    public void setCidade(CidadeRequest cidade) {
        this.cidade = cidade;
    }
}
