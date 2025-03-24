package com.example.desafioprocessoseletivoseplagapi.providers.exceptions;

import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;

public abstract class LayerException extends RuntimeException {

    private LayerEnum layer;
    private String className;

    protected LayerException(String message, LayerDefinition layer) {
        super(message);
        this.layer = layer.getLayer();
        this.className = layer.getClassName();
    }

    public LayerEnum getLayer() {
        return layer;
    }

    public void setLayer(LayerEnum layer) {
        this.layer = layer;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
