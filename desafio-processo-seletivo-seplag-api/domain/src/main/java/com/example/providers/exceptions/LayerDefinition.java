package com.example.providers.exceptions;

import com.example.providers.exceptions.enums.LayerEnum;

public interface LayerDefinition {

    String getClassName();
    LayerEnum getLayer();
}
