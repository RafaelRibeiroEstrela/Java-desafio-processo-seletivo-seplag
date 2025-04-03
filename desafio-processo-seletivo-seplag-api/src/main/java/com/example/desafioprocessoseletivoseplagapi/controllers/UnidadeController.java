package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.CidadeDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.UnidadeDTO;
import com.example.desafioprocessoseletivoseplagapi.services.UnidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Tag(name = "unidade-controller", description = "Operações relacionadas às Unidades")
@RestController
@RequestMapping("/v1/unidades")
public class UnidadeController {

    private final UnidadeService service;

    public UnidadeController(UnidadeService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todas as unidades", description = "Retorna uma página com todas as unidades cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class)))
    })
    @GetMapping
    @Transactional(readOnly = true)
    public Page<UnidadeDTO> findAll(
            @Parameter(description = "Número da página (0-indexado)", example = "0")
            @RequestParam(value = "page", defaultValue = "0") int page,

            @Parameter(description = "Tamanho da página", example = "10")
            @RequestParam(value = "size", defaultValue = "10") int size,

            @Parameter(description = "Critério de ordenação no formato propriedade,ordem (ex: nome,asc)", example = "nome,asc")
            @RequestParam(value = "sort", defaultValue = "nome,asc") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort.split(",")[0])
                .ascending());

        return service.findAll(pageable);
    }

    @Operation(summary = "Criar uma nova unidade", description = "Adiciona uma nova unidade e retorna os dados salvos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Unidade criada com sucesso",
                    content = @Content(schema = @Schema(implementation = UnidadeDTO.class)))
    })
    @PostMapping
    @Transactional
    public ResponseEntity<UnidadeDTO> create(
            @RequestBody @Valid UnidadeDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @Operation(summary = "Excluir uma unidade", description = "Exclui uma unidade com base no ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Unidade excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID da unidade") @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Buscar unidade por ID", description = "Retorna os dados da unidade com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Unidade encontrada",
                    content = @Content(schema = @Schema(implementation = UnidadeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    })
    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public UnidadeDTO findById(
            @Parameter(description = "ID da unidade") @PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Atualizar unidade", description = "Atualiza os dados de uma unidade existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Unidade atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = UnidadeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    })
    @PutMapping("/{id}")
    @Transactional
    public UnidadeDTO update(
            @RequestBody @Valid UnidadeDTO request,
            @Parameter(description = "ID da unidade a ser atualizada") @PathVariable Long id) {
        return service.update(request, id);
    }
}
