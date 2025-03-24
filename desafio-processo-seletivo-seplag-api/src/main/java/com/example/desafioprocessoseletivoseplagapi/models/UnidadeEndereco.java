package com.example.desafioprocessoseletivoseplagapi.models;

import com.example.desafioprocessoseletivoseplagapi.models.ids.UnidadeEnderecoId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "unidade_endereco")
public class UnidadeEndereco {

    @EmbeddedId
    private UnidadeEnderecoId id;

    public UnidadeEndereco() {}

    public UnidadeEndereco(long unidadeId, long enderecoId) {
        this.id = new UnidadeEnderecoId(unidadeId, enderecoId);
    }

    public UnidadeEnderecoId getId() {
        return id;
    }

    public void setId(UnidadeEnderecoId id) {
        this.id = id;
    }
}
