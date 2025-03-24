package com.example.desafioprocessoseletivoseplagapi.utils;

public class QueryUtil {

    public static String aplicarLetraMaiusculaEColocarEntreCoringas(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return "%" + value.toUpperCase() + "%";
    }
}
