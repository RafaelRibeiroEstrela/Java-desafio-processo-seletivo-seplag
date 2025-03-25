package com.example.desafioprocessoseletivoseplagapi.dtos;

import com.example.desafioprocessoseletivoseplagapi.models.Pessoa;
import com.example.desafioprocessoseletivoseplagapi.models.ServidorTemporario;
import com.example.desafioprocessoseletivoseplagapi.providers.dtos.ToModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class ServidorTemporarioDTO implements ToModel<ServidorTemporario> {

    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAdmissao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDemissao;
    private PessoaDTO pessoa;

    public ServidorTemporarioDTO() {}

    public ServidorTemporarioDTO(ServidorTemporario servidorTemporario) {
        this.id = servidorTemporario.getId();
        this.dataAdmissao = servidorTemporario.getDataAdmissao();
        this.dataDemissao = servidorTemporario.getDataDemissao();
    }

    public ServidorTemporarioDTO(ServidorTemporario servidorTemporario, Pessoa pessoa) {
        this(servidorTemporario);
        this.pessoa = new PessoaDTO(pessoa);
    }

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

    public PessoaDTO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaDTO pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public ServidorTemporario toModel() {
        ServidorTemporario model = new ServidorTemporario();
        model.setId(id);
        model.setDataAdmissao(dataAdmissao);
        model.setDataDemissao(dataDemissao);
        return model;
    }
}
