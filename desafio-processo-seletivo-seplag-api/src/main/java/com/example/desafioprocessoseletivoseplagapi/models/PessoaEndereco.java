package com.example.desafioprocessoseletivoseplagapi.models;

import com.example.desafioprocessoseletivoseplagapi.models.ids.PessoaEnderecoId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa_endereco")
public class PessoaEndereco {

    @EmbeddedId
    private PessoaEnderecoId id;

    public PessoaEndereco() {}

    public PessoaEndereco(long pessoaId, long enderecoId) {
        this.id = new PessoaEnderecoId(pessoaId, enderecoId);
    }

    public PessoaEnderecoId getId() {
        return id;
    }

    public void setId(PessoaEnderecoId id) {
        this.id = id;
    }
}
