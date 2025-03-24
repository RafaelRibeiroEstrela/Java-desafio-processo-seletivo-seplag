package com.example.desafioprocessoseletivoseplagapi.providers.exceptions;

public class DatabaseException extends LayerException {

    public DatabaseException(String message, LayerDefinition layerDefinition) {
        super(message, layerDefinition);
    }
}
