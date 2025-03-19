package com.example.providers.exceptions;

public class ResourceNotFoundException extends LayerException {

    public ResourceNotFoundException(String message, LayerDefinition layerDefinition) {
        super(message, layerDefinition);
    }
}
