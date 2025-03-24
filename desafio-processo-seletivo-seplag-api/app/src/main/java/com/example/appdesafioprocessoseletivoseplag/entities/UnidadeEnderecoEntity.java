package com.example.appdesafioprocessoseletivoseplag.entities;

import com.example.appdesafioprocessoseletivoseplag.entities.ids.UnidadeEnderecoId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "unidade_endereco")
public class UnidadeEnderecoEntity {

    @EmbeddedId
    private UnidadeEnderecoId id;

    public UnidadeEnderecoEntity() {}

    public UnidadeEnderecoEntity(long unidadeId, long enderecoId) {
        this.id = new UnidadeEnderecoId(unidadeId, enderecoId);
    }

    public UnidadeEnderecoId getId() {
        return id;
    }

    public void setId(UnidadeEnderecoId id) {
        this.id = id;
    }
}
