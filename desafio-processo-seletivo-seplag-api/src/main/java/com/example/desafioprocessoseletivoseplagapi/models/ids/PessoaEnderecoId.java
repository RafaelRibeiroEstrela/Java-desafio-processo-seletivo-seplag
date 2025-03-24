package com.example.desafioprocessoseletivoseplagapi.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PessoaEnderecoId {

    @Column(name = "pes_id")
    private Long pessoaId;
    @Column(name = "end_id")
    private Long enderecoId;

    public PessoaEnderecoId() {}

    public PessoaEnderecoId(Long pessoaId, Long enderecoId) {
        this.pessoaId = pessoaId;
        this.enderecoId = enderecoId;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }
}
