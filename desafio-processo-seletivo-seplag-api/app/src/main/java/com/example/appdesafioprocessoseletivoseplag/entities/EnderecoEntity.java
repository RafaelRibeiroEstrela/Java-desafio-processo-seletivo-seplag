package com.example.appdesafioprocessoseletivoseplag.entities;

import com.example.models.enums.TipoLogradouroEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "end_id")
    private Long id;
    @Column(name = "end_tipo_logradouro")
    @Enumerated(EnumType.STRING)
    private TipoLogradouroEnum tipoLogradouro;
    @Column(name = "end_logradouro")
    private String logradouro;
    @Column(name = "end_bairro")
    private String bairro;
    @Column(name = "end_numero")
    private String numero;
    @Column(name = "cid_id")
    private Long cidadeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoLogradouroEnum getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouroEnum tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }
}
