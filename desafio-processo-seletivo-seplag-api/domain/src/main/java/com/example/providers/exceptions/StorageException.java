package com.example.providers.exceptions;


public class StorageException extends LayerException {

    public StorageException(String message, LayerDefinition layerDefinition) {
        super(message, layerDefinition);
    }
}
