package com.example.appdesafioprocessoseletivoseplag.utils;

public class QueryUtil {

    public static String aplicarLetraMaiusculaEColocarEntreCoringas(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return "%" + value.toUpperCase() + "%";
    }
}
