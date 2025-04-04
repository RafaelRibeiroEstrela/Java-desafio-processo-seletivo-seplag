package com.example.desafioprocessoseletivoseplagapi.projecoes;

import com.example.desafioprocessoseletivoseplagapi.models.Foto;
import com.example.desafioprocessoseletivoseplagapi.models.Pessoa;
import com.example.desafioprocessoseletivoseplagapi.models.Unidade;

public interface ServidorEfetivoProjection {

    Pessoa getPessoa();
    Unidade getUnidade();
    Foto getFoto();
}
