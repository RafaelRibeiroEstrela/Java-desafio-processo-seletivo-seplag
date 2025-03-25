package com.example.desafioprocessoseletivoseplagapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "servidor_efetivo")
public class ServidorEfetivo {

    @Id
    @Column(name = "pes_id")
    private Long id;
    @Column(name = "se_matricula")
    private Long matricula;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }
}
