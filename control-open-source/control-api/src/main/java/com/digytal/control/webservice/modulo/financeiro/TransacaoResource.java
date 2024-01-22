package com.digytal.control.webservice.modulo.financeiro;

import com.digytal.control.infra.http.response.Response;
import com.digytal.control.infra.http.response.ResponseFactory;
import com.digytal.control.infra.http.response.ResponseMessage;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import com.digytal.control.model.modulo.financeiro.transacao.TransacaoRequest;
import com.digytal.control.service.modulo.financeiro.TransacaoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
@Tag(name = "Recursos referente a lancamento")
public class TransacaoResource {
    @Autowired
    private TransacaoService service;
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R201),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R401),
            @ApiResponse(responseCode = ResponseMessage.R403),
            @ApiResponse(responseCode = ResponseMessage.R409),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    @PostMapping("/despesa")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Response pagar(@RequestBody TransacaoRequest request){
        incluirPagamento(AplicacaoTipo.DESPESA,request);
        return ResponseFactory.create(true,String.format("Pagamento de %,.2f realizado com sucesso", request.getValor()));
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R201),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R401),
            @ApiResponse(responseCode = ResponseMessage.R403),
            @ApiResponse(responseCode = ResponseMessage.R409),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    @PostMapping("/receita")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Response receber(@RequestBody TransacaoRequest request){
        incluirPagamento(AplicacaoTipo.RECEITA,request);
        return ResponseFactory.create(true,String.format("Recebimento de %,.2f realizado com sucesso", request.getValor()));
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R201),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R401),
            @ApiResponse(responseCode = ResponseMessage.R403),
            @ApiResponse(responseCode = ResponseMessage.R409),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    @PostMapping("/tipo/{tipo}")
    @ResponseStatus(code = HttpStatus.CREATED)
    private Response inserir(@PathVariable("tipo") AplicacaoTipo tipo, @RequestBody TransacaoRequest request){
        incluirPagamento(tipo,request);
        return ResponseFactory.create(true,String.format("%s de %,.2f realizada com sucesso", tipo.getDescricao(), request.getValor()));
    }
    private void incluirPagamento(AplicacaoTipo tipo, TransacaoRequest request){
        service.incluir(tipo, request);
    }

}
