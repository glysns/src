package com.digytal.control.webservice.modulo.cadastro.produto;

import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.infra.http.response.Response;
import com.digytal.control.infra.http.response.ResponseFactory;
import com.digytal.control.infra.http.response.ResponseMessage;
import com.digytal.control.model.consulta.lancamento.PagamentoFiltro;
import com.digytal.control.model.consulta.produto.ProdutoFiltro;
import com.digytal.control.model.modulo.cadastro.produto.ProdutoRequest;
import com.digytal.control.model.modulo.cadastro.produto.ProdutoResponse;
import com.digytal.control.repository.modulo.cadastro.ProdutoRepository;
import com.digytal.control.service.modulo.cadastro.produto.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Recursos referente a produto")
public class ProdutoResource {
    private final String LABEL = Entities.PRODUTO_ENTITY.getLabel();
    @Autowired
    private ProdutoService service;

    @Operation(summary = "Incluir um produto", description = "Cadastrar um produto na base de dados")
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
    public Response incluir(@RequestBody ProdutoRequest request) {
        return ResponseFactory.create(service.incluir(request), ResponseMessage.inclusao(Entities.PRODUTO_ENTITY.getLabel()));
    }

    @Operation(summary = "Alterar um produto", description = "Alterar um produto da base de dados")
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R201),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R401),
            @ApiResponse(responseCode = ResponseMessage.R404),
            @ApiResponse(responseCode = ResponseMessage.R409),
    })
    public Response alterar(@PathVariable("id") Integer id, @RequestBody ProdutoRequest request) {
        return ResponseFactory.create(service.alterar(id, request), ResponseMessage.alteracao(Entities.PRODUTO_ENTITY.getLabel()));
    }

    @Operation(summary = "Consulta produtos por nome", description = "Retorna uma lista de produtos filtradas pelo nome")
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

    @GetMapping("/pesquisa")
    public Response pesquisar(ProdutoFiltro filtro ){
        return ResponseFactory.ok(service.pesquisar(filtro),"Consulta realizada com sucesso!");
    }
    @GetMapping("/{id}")
    public Response buscar(@PathVariable("id") Integer id){
        return ResponseFactory.ok(service.buscar(id),"Consulta realizada com sucesso!");
    }

}
