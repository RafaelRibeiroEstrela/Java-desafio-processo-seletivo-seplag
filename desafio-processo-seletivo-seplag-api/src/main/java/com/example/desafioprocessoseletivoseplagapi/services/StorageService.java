package com.example.desafioprocessoseletivoseplagapi.services;

import com.example.desafioprocessoseletivoseplagapi.dtos.FotoDTO;

import java.io.IOException;

public interface StorageService {

    void upload(FotoDTO dto, String bucket, String key);
    FotoDTO download(String bucket, String key) throws IOException;
    void delete(String bucket, String key);
}
