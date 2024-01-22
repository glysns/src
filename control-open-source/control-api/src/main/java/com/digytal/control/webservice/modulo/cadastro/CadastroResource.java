package com.digytal.control.webservice.modulo.cadastro;

import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.infra.http.response.Response;
import com.digytal.control.infra.http.response.ResponseFactory;
import com.digytal.control.infra.http.response.ResponseMessage;
import com.digytal.control.model.comum.cadastramento.CadastroTipo;
import com.digytal.control.model.modulo.cadastro.CadastroRequest;
import com.digytal.control.service.modulo.cadastro.CadastroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastros")
@Tag(name = "Recursos referente a cadastro de clientes e fornecedores")
public class CadastroResource {
    @Autowired
    private CadastroService service;

    @PutMapping("/{id}")
    @Operation(summary = "Alterar um cadastro", description = "Altera um cadastro da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R201),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R401),
            @ApiResponse(responseCode = ResponseMessage.R404),
            @ApiResponse(responseCode = ResponseMessage.R409),
    })
    public Response alterar(@PathVariable("id") Integer id, @RequestBody CadastroRequest request){
        return ResponseFactory.create(service.alterar(id, request),ResponseMessage.alteracao(Entities.CADASTRO_ENTITY.getLabel()));
    }
    @PostMapping()
    @Operation(summary = "Incluir um cadastro", description = "Inclui um cadastro na base de dados")
  
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R201),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R401),
            @ApiResponse(responseCode = ResponseMessage.R403),
            @ApiResponse(responseCode = ResponseMessage.R409),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response incluir(@RequestBody CadastroRequest request){
        return ResponseFactory.create(service.incluir(request),ResponseMessage.inclusao(Entities.CADASTRO_ENTITY.getLabel()));
    }

    @GetMapping("/clientes/nome/{nome}")
    @Operation(summary = "Consulta de clientes por nome", description = "Retorna uma lista do(s) cliente(s) da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R200),
            @ApiResponse(responseCode = ResponseMessage.R204),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response consultarClientes(@PathVariable("nome") String nome){
        return ResponseFactory.ok(service.consultar(CadastroTipo.CLIENTE, nome),ResponseMessage.listagem(Entities.CLIENTE_ENTITY.getLabel()));
    }
    @GetMapping("/fornecedores/nome/{nome}")
    @Operation(summary = "Listar o(s) fornecedor(es)", description = "Retorna uma lista do(s) fornecedor(es) da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R200),
            @ApiResponse(responseCode = ResponseMessage.R204),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response consultarFornecedores(@PathVariable("nome") String nome){
        return ResponseFactory.ok(service.consultar(CadastroTipo.FORNECEDOR,nome),ResponseMessage.listagem(Entities.FORNECEDORES_ENTITY.getLabel()));
    }
    @GetMapping("/tipo/{tipo}/nome/{nome}")
    @Operation(summary = "Consulta de cadastros por tipo e nome", description = "Retorna uma lista dos cadastros com base no tipo e nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R200),
            @ApiResponse(responseCode = ResponseMessage.R204),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response consultar(@PathVariable("tipo") String tipo, @PathVariable("nome") String nome){
        return ResponseFactory.ok(service.consultar(CadastroTipo.valueOf(tipo.toUpperCase()), nome),ResponseMessage.listagem(Entities.CLIENTE_ENTITY.getLabel()));
    }
    @GetMapping("/cpf-cnpj/{cpfCnpj}")
    @Operation(summary = "Busca cadastro por cpf ou cnpj", description = "Retorna um cadastro pelo cpf ou cnpj")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R200),
            @ApiResponse(responseCode = ResponseMessage.R204),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response buscar(@PathVariable("cpfCnpj") String cpfCnpj){
        return ResponseFactory.ok(service.buscar(cpfCnpj),ResponseMessage.busca(Entities.CLIENTE_ENTITY.getLabel()));
    }
}
