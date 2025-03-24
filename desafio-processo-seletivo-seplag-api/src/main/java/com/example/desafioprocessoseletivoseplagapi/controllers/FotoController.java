package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.FotoDTO;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.LayerDefinition;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.StorageException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;
import com.example.desafioprocessoseletivoseplagapi.services.FotoService;
import com.example.desafioprocessoseletivoseplagapi.utils.FileUtil;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/pessoas/fotos")
public class FotoController implements LayerDefinition {

    private final FotoService service;

    private final FileUtil fileUtil;

    public FotoController(FotoService service, FileUtil fileUtil) {
        this.service = service;
        this.fileUtil = fileUtil;
    }

    @Transactional
    @PutMapping("/upload/{id}")
    public List<FotoDTO> uploadFotos(@RequestParam("files") List<MultipartFile> files, @PathVariable Long id) {
        return files.stream().map(file -> {
            FotoDTO dto = new FotoDTO();
            dto.setFilename(file.getOriginalFilename());
            dto.setContentType(file.getContentType());
            try {
                dto.setContent(file.getBytes());
            } catch (IOException e) {
                throw new StorageException("Erro ao obter arquivo: " + e.getMessage(), this);
            }
            return service.upload(dto, id);
        }).toList();
    }

    @Transactional(readOnly = true)
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) {
        List<FotoDTO> dtos = service.download(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "arquivos.zip" + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(new ByteArrayInputStream(fileUtil.compactFilesToZip(dtos))));
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public LayerEnum getLayer() {
        return LayerEnum.API_CONTROLLER;
    }
}
