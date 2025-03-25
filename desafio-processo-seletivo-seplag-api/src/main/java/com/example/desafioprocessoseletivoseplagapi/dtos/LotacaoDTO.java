package com.example.desafioprocessoseletivoseplagapi.dtos;

import com.example.desafioprocessoseletivoseplagapi.models.Lotacao;
import com.example.desafioprocessoseletivoseplagapi.models.Pessoa;
import com.example.desafioprocessoseletivoseplagapi.models.Unidade;
import com.example.desafioprocessoseletivoseplagapi.providers.dtos.ToModel;

import java.time.LocalDate;

public class LotacaoDTO implements ToModel<Lotacao> {

    private Long id;
    private LocalDate dataLotacao;
    private LocalDate dataRemocao;
    private String portaria;
    private PessoaDTO pessoa;
    private UnidadeDTO unidade;

    public LotacaoDTO() {}

    public LotacaoDTO(Lotacao lotacao) {
        this.id = lotacao.getId();
        this.dataLotacao = lotacao.getDataLotacao();
        this.dataRemocao = lotacao.getDataRemocao();
        this.portaria = lotacao.getPortaria();
    }

    public LotacaoDTO(Lotacao lotacao, Pessoa pessoa, Unidade unidade) {
        this(lotacao);
        if (pessoa != null) {
            this.pessoa = new PessoaDTO(pessoa);
        }
        if (unidade != null) {
            this.unidade = new UnidadeDTO(unidade);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataLotacao() {
        return dataLotacao;
    }

    public void setDataLotacao(LocalDate dataLotacao) {
        this.dataLotacao = dataLotacao;
    }

    public LocalDate getDataRemocao() {
        return dataRemocao;
    }

    public void setDataRemocao(LocalDate dataRemocao) {
        this.dataRemocao = dataRemocao;
    }

    public String getPortaria() {
        return portaria;
    }

    public void setPortaria(String portaria) {
        this.portaria = portaria;
    }

    public PessoaDTO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaDTO pessoa) {
        this.pessoa = pessoa;
    }

    public UnidadeDTO getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeDTO unidade) {
        this.unidade = unidade;
    }

    @Override
    public Lotacao toModel() {
        Lotacao model = new Lotacao();
        model.setId(id);
        model.setDataLotacao(dataLotacao);
        model.setDataRemocao(dataRemocao);
        model.setPortaria(portaria);
        return model;
    }
}
