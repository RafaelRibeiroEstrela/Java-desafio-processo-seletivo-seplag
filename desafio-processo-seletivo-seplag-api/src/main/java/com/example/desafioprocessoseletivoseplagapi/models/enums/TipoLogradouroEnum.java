package com.example.desafioprocessoseletivoseplagapi.models.enums;

public enum TipoLogradouroEnum {
    AEROPORTO,
    ALAMEDA,
    AREA,
    AVENIDA,
    CAMPO,
    CHACARA,
    COLONIA,
    CONDOMINIO,
    CONJUNTO,
    DISTRITO,
    ESPLANADA,
    ESTACAO,
    ESTRADA,
    FAVELA,
    FAZENDA,
    FEIRA,
    JARDIM,
    LADEIRA,
    LAGO,
    LAGOA,
    LARGO,
    LOTEAMENTO,
    MORRO,
    NUCLEO,
    PARQUE,
    PASSARELA,
    PATIO,
    PRACA,
    QUADRA,
    RECANTO,
    RESIDENCIAL,
    RODOVIA,
    RUA,
    SETOR,
    SITIO,
    TRAVESSA,
    TRECHO,
    TREVO,
    VALE,
    VEREDA,
    VIA,
    VIADUTO,
    VIELA,
    VILA;

    public static TipoLogradouroEnum toEnum(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return TipoLogradouroEnum.valueOf(value.toUpperCase());
    }
}
