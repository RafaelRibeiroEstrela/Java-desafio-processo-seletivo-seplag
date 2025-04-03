package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.CidadeDTO;
import com.example.desafioprocessoseletivoseplagapi.services.CidadeService;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "cidade-controller", description = "Operações relacionadas às cidades")
@RestController
@RequestMapping("/v1/cidades")
public class CidadeController {
    
    private final CidadeService service;

    public CidadeController(CidadeService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todas as cidades", description = "Retorna uma página com todas as cidades cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class)))
    })
    @GetMapping
    @Transactional(readOnly = true)
    public Page<CidadeDTO> findAll(
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
}
