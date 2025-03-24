package com.example.desafioprocessoseletivoseplagapi.providers.exceptions;

import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;

public interface LayerDefinition {

    String getClassName();
    LayerEnum getLayer();
}
