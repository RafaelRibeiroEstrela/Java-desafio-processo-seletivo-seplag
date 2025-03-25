package com.example.desafioprocessoseletivoseplagapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "servidor_temporario")
public class ServidorTemporario {

    @Id
    @Column(name = "pes_id")
    private Long id;
    @Column(name = "st_data_admissao")
    private LocalDate dataAdmissao;
    @Column(name = "st_data_demissao")
    private LocalDate dataDemissao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public LocalDate getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(LocalDate dataDemissao) {
        this.dataDemissao = dataDemissao;
    }
}
