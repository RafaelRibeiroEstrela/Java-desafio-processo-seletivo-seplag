package com.example.desafioprocessoseletivoseplagapi.providers.exceptions;

public class BusinessException extends LayerException {

    public BusinessException(String message, LayerDefinition layerDefinition) {
        super(message, layerDefinition);
    }
}
