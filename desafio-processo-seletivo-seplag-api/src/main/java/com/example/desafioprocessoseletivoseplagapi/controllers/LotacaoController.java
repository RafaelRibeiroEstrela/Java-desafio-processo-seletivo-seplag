package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.CidadeDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.LotacaoDTO;
import com.example.desafioprocessoseletivoseplagapi.services.LotacaoService;
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

@Tag(name = "lotacao-controller", description = "Operações relacionadas às lotações")
@RestController
@RequestMapping("/v1/lotacoes")
public class LotacaoController {
    
    private final LotacaoService service;

    public LotacaoController(LotacaoService service) {
        this.service = service;
    }

    @Operation(summary = "Criar uma nova lotação", description = "Cria uma nova lotação e retorna os dados criados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lotação criada com sucesso",
                    content = @Content(schema = @Schema(implementation = LotacaoDTO.class)))
    })
    @Transactional
    @PostMapping
    public ResponseEntity<LotacaoDTO> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados da nova lotação",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LotacaoDTO.class))
            )
            @RequestBody LotacaoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Excluir uma lotação", description = "Exclui a lotação com base no ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lotação excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lotação não encontrada")
    })
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID da lotação", required = true) @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Buscar lotação por ID", description = "Retorna a lotação com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lotação encontrada",
                    content = @Content(schema = @Schema(implementation = LotacaoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Lotação não encontrada")
    })
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public LotacaoDTO findById(
            @Parameter(description = "ID da lotação", required = true) @PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Atualizar lotação", description = "Atualiza os dados da lotação com base no ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lotação atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = LotacaoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Lotação não encontrada")
    })
    @Transactional
    @PutMapping("/{id}")
    public LotacaoDTO update(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados atualizados da lotação",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LotacaoDTO.class))
            )
            @RequestBody LotacaoDTO dto,
            @Parameter(description = "ID da lotação a ser atualizada", required = true) @PathVariable Long id) {
        return service.update(dto, id);
    }

    @Operation(summary = "Listar todas as lotações", description = "Retorna uma página com todas as lotações cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class)))
    })
    @Transactional(readOnly = true)
    @GetMapping
    public Page<LotacaoDTO> findAll(
            @Parameter(description = "Número da página (0-indexado)", example = "0")
            @RequestParam(value = "page", defaultValue = "0") int page,

            @Parameter(description = "Tamanho da página", example = "10")
            @RequestParam(value = "size", defaultValue = "10") int size,

            @Parameter(description = "Critério de ordenação no formato propriedade,ordem (ex: nome,asc)", example = "dataLotacao,asc")
            @RequestParam(value = "sort", defaultValue = "dataLotacao,asc") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort.split(",")[0])
                .ascending());

        return service.findAll(pageable);
    }
}
