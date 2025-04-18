package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.FotoDTO;
import com.example.desafioprocessoseletivoseplagapi.services.FotoService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class DownloadFotoController {

    private final FotoService fotoService;

    public DownloadFotoController(FotoService fotoService) {
        this.fotoService = fotoService;
    }

    @Hidden
    @GetMapping("/minio/desafio-processo-seletivo-seplag-bucket/{key}")
    public ResponseEntity<InputStreamResource> download(@PathVariable String key) {
        FotoDTO dto = fotoService.download(key);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + dto.getFilename())
                .contentType(MediaType.parseMediaType(dto.getContentType()))
                .contentLength(dto.getContent().length)
                .body(new InputStreamResource(new ByteArrayInputStream(dto.getContent())));
    }
}
