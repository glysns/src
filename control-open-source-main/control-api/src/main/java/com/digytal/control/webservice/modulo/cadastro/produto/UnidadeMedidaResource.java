package com.digytal.control.webservice.modulo.cadastro.produto;

import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.infra.http.response.Response;
import com.digytal.control.infra.http.response.ResponseFactory;
import com.digytal.control.infra.http.response.ResponseMessage;
import com.digytal.control.model.modulo.cadastro.produto.unidademedida.UnidadeMedidaRequest;
import com.digytal.control.service.modulo.cadastro.produto.UnidadeMedidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unidades-medida")
@Tag(name = "Recursos referente a unidade medida")
public class UnidadeMedidaResource {
    private final String LABEL = Entities.UNIDADE_MEDIDA_ENTITY.getLabel();
    @Autowired
    private UnidadeMedidaService service;

    @Operation(summary = "Inclusão de uma unidade de medida", description = "Cadastra uma unidade de medida na base de dados")
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
    public Response incluir( @RequestBody UnidadeMedidaRequest request) {
        return ResponseFactory.create(service.incluir(request), ResponseMessage.inclusao(LABEL));
    }

    @Operation(summary = "Busca uma unidade de medida pelo seu id", description = "Procura uma unidade de medida pelo seu id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R200),
            @ApiResponse(responseCode = ResponseMessage.R204),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response buscar(@PathVariable(value = "id") Integer id) {
        return ResponseFactory.ok(service.buscar(id),ResponseMessage.busca(LABEL));
    }
    @Operation(summary = "Alteração da unidade de medida selecionada", description = "Altera uma unidade de medida da base de dados")
    @PutMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R201),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R401),
            @ApiResponse(responseCode = ResponseMessage.R404),
            @ApiResponse(responseCode = ResponseMessage.R409),
    })

    public Response alterar(@PathVariable(value = "id") Integer id, @RequestBody UnidadeMedidaRequest request){
        return ResponseFactory.create(service.alterar(id, request), ResponseMessage.alteracao(LABEL));
    }

    @Operation(summary = "Consulta unidades de medida por nome", description = "Retorna um lista de unidades de medida filtradas pelo nome")
    @GetMapping(value = {"", "/nome/{nome}"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R200),
            @ApiResponse(responseCode = ResponseMessage.R204),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response consultar(@PathVariable(value = "nome", required = false) String nome) {
        return ResponseFactory.ok(service.consultar(nome),ResponseMessage.consulta(LABEL));
    }
    @Operation(summary = "Listagem resumida das unidades de medida", description = "Retorna uma listagem com estrutura resumida das unidades de medida")
    @GetMapping(value = {"/listagem", "/listagem/{nome}"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R200),
            @ApiResponse(responseCode = ResponseMessage.R204),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response listar(@PathVariable(value = "nome", required = false) String nome) {
        return ResponseFactory.ok(service.listar(nome),ResponseMessage.listagem(LABEL));
    }
}
