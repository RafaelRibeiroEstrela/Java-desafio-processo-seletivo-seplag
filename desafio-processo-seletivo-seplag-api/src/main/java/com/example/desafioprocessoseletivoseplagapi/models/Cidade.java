package com.example.desafioprocessoseletivoseplagapi.models;

import com.example.desafioprocessoseletivoseplagapi.models.enums.UfEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid_id")
    private Long id;
    @Column(name = "cid_nome")
    private String nome;
    @Column(name = "cid_uf")
    @Enumerated(EnumType.STRING)
    private UfEnum uf;

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
}
