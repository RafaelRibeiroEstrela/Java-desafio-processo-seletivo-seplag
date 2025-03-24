package com.example.desafioprocessoseletivoseplagapi.utils;

import com.example.desafioprocessoseletivoseplagapi.dtos.FotoDTO;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.LayerDefinition;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.StorageException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class FileUtil implements LayerDefinition {

    public byte[] compactFilesToZip(List<FotoDTO> files) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
                for (FotoDTO file : files) {
                    if (file.getContent() != null) {
                        // Criando uma nova entrada para cada arquivo no ZIP
                        ZipEntry zipEntry = new ZipEntry(file.getFilename());
                        zipOutputStream.putNextEntry(zipEntry);

                        // Escrevendo os dados do arquivo
                        zipOutputStream.write(file.getContent());
                        zipOutputStream.closeEntry();
                    }
                }
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new StorageException("Falha ao converter arquivos para zip: " + e.getMessage(), this);
        }
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_COMPONENT;
    }
}
