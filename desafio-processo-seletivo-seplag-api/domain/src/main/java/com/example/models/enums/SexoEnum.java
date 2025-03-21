package com.example.models.enums;

public enum SexoEnum {

    MASCULINO,
    FEMININO;

    public static SexoEnum toEnum(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return SexoEnum.valueOf(value.toUpperCase());
    }
}
