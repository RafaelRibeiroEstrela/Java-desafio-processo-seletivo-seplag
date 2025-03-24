package com.example.desafioprocessoseletivoseplagapi.dtos;

import com.example.desafioprocessoseletivoseplagapi.models.Endereco;
import com.example.desafioprocessoseletivoseplagapi.models.Foto;
import com.example.desafioprocessoseletivoseplagapi.models.Pessoa;
import com.example.desafioprocessoseletivoseplagapi.models.enums.SexoEnum;
import com.example.desafioprocessoseletivoseplagapi.providers.dtos.ToModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoaDTO implements ToModel<Pessoa> {

    private Long id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private SexoEnum sexo;
    private String nomeMae;
    private String nomePai;
    private List<EnderecoDTO> enderecos = new ArrayList<>();
    private List<FotoDTO> fotos = new ArrayList<>();

    public PessoaDTO() {}

    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.dataNascimento = pessoa.getDataNascimento();
        this.sexo = pessoa.getSexo();
        this.nomeMae = pessoa.getNomeMae();
        this.nomePai = pessoa.getNomePai();
    }

    public PessoaDTO(Pessoa pessoa, List<Endereco> enderecos, List<Foto> fotos) {
        this(pessoa);
        this.enderecos = enderecos.stream().map(EnderecoDTO::new).toList();
        this.fotos = fotos.stream().map(FotoDTO::new).toList();
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }

    public List<FotoDTO> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoDTO> fotos) {
        this.fotos = fotos;
    }

    @Override
    public Pessoa toModel() {
        Pessoa model = new Pessoa();
        model.setId(id);
        model.setNome(nome);
        model.setDataNascimento(dataNascimento);
        model.setSexo(sexo);
        model.setNomeMae(nomeMae);
        model.setNomePai(nomePai);
        return model;
    }
}
