package com.digytal.control.webservice.modulo.cadastro.produto;

import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.infra.http.response.Response;
import com.digytal.control.infra.http.response.ResponseFactory;
import com.digytal.control.infra.http.response.ResponseMessage;
import com.digytal.control.model.modulo.cadastro.produto.categoria.CategoriaRequest;
import com.digytal.control.service.modulo.cadastro.produto.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
@Tag(name = "Recursos referente a categoria")
public class CategoriaResource {
    @Autowired
    private CategoriaService service;

    @Operation(summary = "Incluir uma categorias", description = "Cadastrar uma categoria na base de dados")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R201),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R401),
            @ApiResponse(responseCode = ResponseMessage.R403),
            @ApiResponse(responseCode = ResponseMessage.R409),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    public Response incluir(@RequestBody CategoriaRequest request) {
        return ResponseFactory.create(service.incluir(request), ResponseMessage.inclusao(Entities.CATEGORIA_ENTITY.getLabel()));
    }
    @Operation(summary = "busca uma categoria pelo seu id", description = "busca uma categoria pelo seu id")
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
    @Operation(summary = "Alterar uma categoria", description = "Alterar uma categoria da base de dados")
    @PutMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R201),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R401),
            @ApiResponse(responseCode = ResponseMessage.R404),
            @ApiResponse(responseCode = ResponseMessage.R409),
    })
    public Response alterar(@PathVariable(value = "id") Integer id, @RequestBody CategoriaRequest request){
        return ResponseFactory.create(service.alterar(id, request), ResponseMessage.alteracao(Entities.CATEGORIA_ENTITY.getLabel()));
    }

    @Operation(summary = "consulta categoria(s) por nome", description = "Retorna um filtro dos registros de categoria")
    @GetMapping(value = {"", "/nome/{nome}"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R200),
            @ApiResponse(responseCode = ResponseMessage.R204),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response consultar(@PathVariable(value = "nome") String nome) {
        return ResponseFactory.ok(service.consultar(nome),ResponseMessage.listagem(Entities.CATEGORIA_ENTITY.getLabel()));
    }
    @Operation(summary = "lista as categorias de forma resumida", description = "Retorna a lista resumida de categorias")
    @GetMapping(value = {"/listagem", "/listagem/{nome}"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R200),
            @ApiResponse(responseCode = ResponseMessage.R204),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response listar(@PathVariable(value = "nome", required = false) String nome) {
        return ResponseFactory.ok(service.listar(nome),ResponseMessage.listagem(Entities.CATEGORIA_ENTITY.getLabel()));
    }
}
