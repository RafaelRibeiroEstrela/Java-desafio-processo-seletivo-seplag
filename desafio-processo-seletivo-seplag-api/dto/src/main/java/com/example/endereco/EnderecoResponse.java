package com.example.endereco;

import com.example.cidade.CidadeResponse;

public class EnderecoResponse {

    private Long id;
    private String tipoLogradouro;
    private String logradouro;
    private String numero;
    private String bairro;
    private CidadeResponse cidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public CidadeResponse getCidade() {
        return cidade;
    }

    public void setCidade(CidadeResponse cidade) {
        this.cidade = cidade;
    }
}
