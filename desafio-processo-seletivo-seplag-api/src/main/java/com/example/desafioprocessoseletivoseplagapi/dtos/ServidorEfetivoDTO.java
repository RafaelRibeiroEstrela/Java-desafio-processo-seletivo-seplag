package com.example.desafioprocessoseletivoseplagapi.dtos;

import com.example.desafioprocessoseletivoseplagapi.models.Pessoa;
import com.example.desafioprocessoseletivoseplagapi.models.ServidorEfetivo;
import com.example.desafioprocessoseletivoseplagapi.providers.dtos.ToModel;

public class ServidorEfetivoDTO implements ToModel<ServidorEfetivo> {

    private Long id;
    private Long matricula;
    private PessoaDTO pessoa;

    public ServidorEfetivoDTO() {}

    public ServidorEfetivoDTO(ServidorEfetivo servidorEfetivo) {
        this.id = servidorEfetivo.getId();
        this.matricula = servidorEfetivo.getMatricula();
    }

    public ServidorEfetivoDTO(ServidorEfetivo servidorEfetivo, Pessoa pessoa) {
        this(servidorEfetivo);
        if (pessoa != null) {
            this.pessoa = new PessoaDTO(pessoa);
        }
    }

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

    public PessoaDTO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaDTO pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public ServidorEfetivo toModel() {
        ServidorEfetivo model = new ServidorEfetivo();
        model.setId(id);
        model.setMatricula(matricula);
        return model;
    }
}
