package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.CidadeDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.ServidorEfetivoDTO;
import com.example.desafioprocessoseletivoseplagapi.services.ServidorEfetivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Tag(name = "servidor-efetivo-controller", description = "Operações relacionadas aos servidores efetivos")
@RestController
@RequestMapping("/v1/servidores-efetivos")
public class ServidorEfetivoController {

    private final ServidorEfetivoService service;

    public ServidorEfetivoController(ServidorEfetivoService service) {
        this.service = service;
    }

    @Operation(summary = "Criar servidor efetivo", description = "Cria um novo servidor efetivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Servidor efetivo criado com sucesso",
                    content = @Content(schema = @Schema(implementation = ServidorEfetivoDTO.class)))
    })
    @Transactional
    @PostMapping
    public ResponseEntity<ServidorEfetivoDTO> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do servidor efetivo",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ServidorEfetivoDTO.class))
            )
            @RequestBody ServidorEfetivoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Excluir servidor efetivo", description = "Exclui o servidor efetivo com base no ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Servidor efetivo excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Servidor efetivo não encontrado")
    })
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do servidor efetivo", required = true) @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Listar servidores efetivos", description = "Retorna uma página com os servidores efetivos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class)))
    })
    @Transactional(readOnly = true)
    @GetMapping
    public Page<ServidorEfetivoDTO> findAll(
            @Parameter(description = "Número da página (0-indexado)", example = "0")
            @RequestParam(value = "page", defaultValue = "0") int page,

            @Parameter(description = "Tamanho da página", example = "10")
            @RequestParam(value = "size", defaultValue = "10") int size,

            @Parameter(description = "Critério de ordenação no formato propriedade,ordem (ex: nome,asc)", example = "matricula,asc")
            @RequestParam(value = "sort", defaultValue = "matricula,asc") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort.split(",")[0])
                .ascending());

        return service.findAll(pageable);
    }

    @Operation(summary = "Buscar servidor efetivo por ID", description = "Retorna os dados do servidor efetivo com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servidor efetivo encontrado",
                    content = @Content(schema = @Schema(implementation = ServidorEfetivoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Servidor efetivo não encontrado")
    })
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ServidorEfetivoDTO findById(
            @Parameter(description = "ID do servidor efetivo", required = true) @PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Atualizar servidor efetivo", description = "Atualiza os dados do servidor efetivo com base no ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servidor efetivo atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = ServidorEfetivoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Servidor efetivo não encontrado")
    })
    @Transactional
    @PutMapping("/{id}")
    public ServidorEfetivoDTO update(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados atualizados do servidor efetivo",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ServidorEfetivoDTO.class))
            )
            @RequestBody ServidorEfetivoDTO dto,
            @Parameter(description = "ID do servidor efetivo a ser atualizado", required = true) @PathVariable Long id) {
        return service.update(dto, id);
    }
}
