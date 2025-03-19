package com.example.providers.exceptions;

public class TokenException extends LayerException {

    public TokenException(String message, LayerDefinition layerDefinition) {
        super(message, layerDefinition);
    }
}
