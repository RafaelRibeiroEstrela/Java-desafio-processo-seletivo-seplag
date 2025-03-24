package com.example.desafioprocessoseletivoseplagapi.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UnidadeEnderecoId {

    @Column(name = "unid_id")
    private Long unidadeId;
    @Column(name = "end_id")
    private Long enderecoId;

    public UnidadeEnderecoId() {}

    public UnidadeEnderecoId(long unidadeId, long enderecoId) {
        this.unidadeId = unidadeId;
        this.enderecoId = enderecoId;
    }

    public Long getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(Long unidadeId) {
        this.unidadeId = unidadeId;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }
}
