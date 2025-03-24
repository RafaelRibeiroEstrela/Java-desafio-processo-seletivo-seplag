package com.example.desafioprocessoseletivoseplagapi.dtos;

import com.example.desafioprocessoseletivoseplagapi.models.Endereco;
import com.example.desafioprocessoseletivoseplagapi.models.Unidade;
import com.example.desafioprocessoseletivoseplagapi.providers.dtos.ToModel;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class UnidadeDTO implements ToModel<Unidade> {

    private Long id;
    @NotEmpty(message = "O nome da unidade é obrigatório")
    private String nome;
    @NotEmpty(message = "A sigla da unidade é obrigatório")
    private String sigla;
    private List<EnderecoDTO> enderecos = new ArrayList<>();

    public UnidadeDTO() {}

    public UnidadeDTO(Unidade unidade) {
        this.id = unidade.getId();
        this.nome = unidade.getNome();
        this.sigla = unidade.getSigla();
    }

    public UnidadeDTO(Unidade unidade, List<Endereco> enderecos) {
        this(unidade);
        this.enderecos = enderecos.stream().map(EnderecoDTO::new).toList();
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public Unidade toModel() {
        Unidade model = new Unidade();
        model.setId(id);
        model.setNome(nome);
        model.setSigla(sigla);
        return model;
    }
}
