package com.example.desafioprocessoseletivoseplagapi.models;

import com.example.desafioprocessoseletivoseplagapi.models.enums.SexoEnum;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pes_id")
    private Long id;
    @Column(name = "pes_nome")
    private String nome;
    @Column(name = "pes_data_nascimento")
    private LocalDate dataNascimento;
    @Column(name = "pes_sexo")
    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;
    @Column(name = "pes_mae")
    private String nomeMae;
    @Column(name = "pes_pai")
    private String nomePai;

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
}
