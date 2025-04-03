package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.FotoDTO;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.LayerDefinition;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.StorageException;
import com.example.desafioprocessoseletivoseplagapi.providers.exceptions.enums.LayerEnum;
import com.example.desafioprocessoseletivoseplagapi.services.FotoService;
import com.example.desafioprocessoseletivoseplagapi.utils.FileUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "foto-controller", description = "Operações relacionadas às fotos")
@RestController
@RequestMapping("/v1/fotos")
public class FotoController implements LayerDefinition {

    private final FotoService service;

    private final FileUtil fileUtil;

    public FotoController(FotoService service, FileUtil fileUtil) {
        this.service = service;
        this.fileUtil = fileUtil;
    }

    @Operation(summary = "Upload de fotos", description = "Realiza o upload de uma ou mais fotos para a pessoa especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fotos enviadas com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FotoDTO[].class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro ao processar o arquivo")
    })
    @Transactional
    @PutMapping("/upload/{pessoaId}")
    public List<FotoDTO> uploadFotos(
            @Parameter(description = "Lista de arquivos de foto a serem enviados", required = true)
            @RequestParam("files") List<MultipartFile> files,
            @Parameter(description = "ID da pessoa", required = true)
            @PathVariable Long pessoaId) {
        return files.stream().map(file -> {
            FotoDTO dto = new FotoDTO();
            dto.setFilename(file.getOriginalFilename());
            dto.setContentType(file.getContentType());
            try {
                dto.setContent(file.getBytes());
            } catch (IOException e) {
                throw new StorageException("Erro ao obter arquivo: " + e.getMessage(), this);
            }
            return service.upload(dto, pessoaId);
        }).toList();
    }

    @Operation(summary = "Download de fotos", description = "Realiza o download de um arquivo ZIP contendo as fotos associadas à pessoa informada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arquivo ZIP gerado com sucesso",
                    content = @Content(mediaType = "application/octet-stream")),
            @ApiResponse(responseCode = "404", description = "Fotos não encontradas para a pessoa especificada"),
            @ApiResponse(responseCode = "500", description = "Erro ao gerar o arquivo ZIP")
    })
    @Transactional(readOnly = true)
    @GetMapping("/download/{pessoaId}")
    public ResponseEntity<Resource> download(
            @Parameter(description = "ID da pessoa", required = true)
            @PathVariable Long pessoaId) {
        List<FotoDTO> dtos = service.download(pessoaId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"arquivos.zip\"")
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
