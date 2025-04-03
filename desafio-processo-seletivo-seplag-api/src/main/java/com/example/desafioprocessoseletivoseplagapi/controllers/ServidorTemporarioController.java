package com.example.desafioprocessoseletivoseplagapi.controllers;

import com.example.desafioprocessoseletivoseplagapi.dtos.CidadeDTO;
import com.example.desafioprocessoseletivoseplagapi.dtos.ServidorTemporarioDTO;
import com.example.desafioprocessoseletivoseplagapi.services.ServidorTemporarioService;
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

@Tag(name = "servidor-temporario-controller", description = "Operações relacionadas aos servidores temporários")
@RestController
@RequestMapping("/v1/servidores-temporarios")
public class ServidorTemporarioController {

    private final ServidorTemporarioService service;

    public ServidorTemporarioController(ServidorTemporarioService service) {
        this.service = service;
    }

    @Operation(summary = "Criar servidor temporário", description = "Cria um novo servidor temporário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Servidor temporário criado com sucesso",
                    content = @Content(schema = @Schema(implementation = ServidorTemporarioDTO.class)))
    })
    @Transactional
    @PostMapping
    public ResponseEntity<ServidorTemporarioDTO> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do servidor temporário",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ServidorTemporarioDTO.class))
            )
            @RequestBody ServidorTemporarioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Excluir servidor temporário", description = "Exclui o servidor temporário com base no ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Servidor temporário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Servidor temporário não encontrado")
    })
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do servidor temporário", required = true) @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Listar servidores temporários", description = "Retorna uma página com os servidores temporários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class)))
    })
    @Transactional(readOnly = true)
    @GetMapping
    public Page<ServidorTemporarioDTO> findAll(
            @Parameter(description = "Número da página (0-indexado)", example = "0")
            @RequestParam(value = "page", defaultValue = "0") int page,

            @Parameter(description = "Tamanho da página", example = "10")
            @RequestParam(value = "size", defaultValue = "10") int size,

            @Parameter(description = "Critério de ordenação no formato propriedade,ordem (ex: nome,asc)", example = "dataAdmissao,asc")
            @RequestParam(value = "sort", defaultValue = "dataAdmissao,asc") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort.split(",")[0])
                .ascending());

        return service.findAll(pageable);
    }

    @Operation(summary = "Buscar servidor temporário por ID", description = "Retorna os dados do servidor temporário com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servidor temporário encontrado",
                    content = @Content(schema = @Schema(implementation = ServidorTemporarioDTO.class))),
            @ApiResponse(responseCode = "404", description = "Servidor temporário não encontrado")
    })
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ServidorTemporarioDTO findById(
            @Parameter(description = "ID do servidor temporário", required = true) @PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Atualizar servidor temporário", description = "Atualiza os dados do servidor temporário com base no ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servidor temporário atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = ServidorTemporarioDTO.class))),
            @ApiResponse(responseCode = "404", description = "Servidor temporário não encontrado")
    })
    @Transactional
    @PutMapping("/{id}")
    public ServidorTemporarioDTO update(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados atualizados do servidor temporário",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ServidorTemporarioDTO.class))
            )
            @RequestBody ServidorTemporarioDTO dto,
            @Parameter(description = "ID do servidor temporário a ser atualizado", required = true) @PathVariable Long id) {
        return service.update(dto, id);
    }
}
