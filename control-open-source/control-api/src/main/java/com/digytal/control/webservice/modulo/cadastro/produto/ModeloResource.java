package com.digytal.control.webservice.modulo.cadastro.produto;

import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.infra.http.response.Response;
import com.digytal.control.infra.http.response.ResponseFactory;
import com.digytal.control.infra.http.response.ResponseMessage;
import com.digytal.control.model.modulo.cadastro.produto.modelo.ModeloRequest;
import com.digytal.control.service.modulo.cadastro.produto.ModeloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modelos")
@Tag(name = "Recursos referente ao modelo")
public class ModeloResource {
    @Autowired
    private ModeloService service;

    @Operation(summary = "Incluir um modelo", description = "Cadastrar um modelo na base de dados")
    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R201),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R401),
            @ApiResponse(responseCode = ResponseMessage.R403),
            @ApiResponse(responseCode = ResponseMessage.R409),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    public Response incluir(@RequestBody ModeloRequest request) {
        return ResponseFactory.create(service.incluir(request), ResponseMessage.inclusao(Entities.MODELO_ENTITY.getLabel()));
    }

    @Operation(summary = "busca um modelo pelo seu id", description = "busca um modelo pelo seu id")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R200),
            @ApiResponse(responseCode = ResponseMessage.R204),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response buscar(@PathVariable(value = "id") Integer id) {
        return ResponseFactory.ok(service.buscar(id),ResponseMessage.listagem(Entities.MARCA_ENTITY.getLabel()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar um modelo", description = "Alterar um modelo da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R201),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R401),
            @ApiResponse(responseCode = ResponseMessage.R404),
            @ApiResponse(responseCode = ResponseMessage.R409),
    })
    public Response alterar(@PathVariable("id") Integer id, @RequestBody ModeloRequest request) {
        return ResponseFactory.create(service.alterar(id, request), ResponseMessage.inclusao(Entities.MODELO_ENTITY.getLabel()));
    }

    @Operation(summary = "consultar modelos(s) por nome", description = "Retorna um filtro dos registros de modelo")
    @GetMapping(value = {"", "/nome/{nome}"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R200),
            @ApiResponse(responseCode = ResponseMessage.R204),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response consultar(@PathVariable(value = "nome") String nome) {
        return ResponseFactory.ok(service.consultar(nome),ResponseMessage.listagem(Entities.MARCA_ENTITY.getLabel()));
    }
    @Operation(summary = "lista os modelos de forma resumida", description = "Retorna a lista resumida de modelos")
    @GetMapping(value = {"/listagem", "/listagem/{nome}"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R200),
            @ApiResponse(responseCode = ResponseMessage.R204),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response listar(@PathVariable(value = "nome", required = false) String nome) {
        return ResponseFactory.ok(service.listar(nome),ResponseMessage.listagem(Entities.MARCA_ENTITY.getLabel()));
    }
}
