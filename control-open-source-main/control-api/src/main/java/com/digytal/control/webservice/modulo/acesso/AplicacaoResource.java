package com.digytal.control.webservice.modulo.acesso;

import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.infra.http.response.Response;
import com.digytal.control.infra.http.response.ResponseFactory;
import com.digytal.control.infra.http.response.ResponseMessage;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoRequest;
import com.digytal.control.service.modulo.acesso.AplicacaoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aplicacoes")
@Tag(name = "Recursos referente à aplicações")
public class AplicacaoResource {
    @Autowired
    private AplicacaoService service;
    /*
    @GetMapping("/areas/{nome}")
    public Response listarAreas(@PathVariable("nome") String nome){
        return ResponseFactory.ok(service.listarAreas(nome),"Consulta realizada com sucesso");
    }
    @GetMapping("/receitas/{nome}")
    public Response listarReceitas(@PathVariable("nome") String nome){
        return ResponseFactory.ok(service.listarReceitas(nome),"Consulta realizada com sucesso");
    }
    @GetMapping("/despesas/{nome}")
    public Response listarDespesas(@PathVariable("nome") String nome){
        return ResponseFactory.ok(service.listarDespesas(nome),"Consulta realizada com sucesso");
    }
    */
    @GetMapping("/areas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R200),
            @ApiResponse(responseCode = ResponseMessage.R204),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response listarAreas(){
        return ResponseFactory.ok(service.listarAreas(""),ResponseMessage.listagem(Entities.APLICACAO_ENTITY.getLabel()));
    }
    @GetMapping("/receitas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R200),
            @ApiResponse(responseCode = ResponseMessage.R204),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response listarReceitas(){
        return ResponseFactory.ok(service.listarReceitas(""),ResponseMessage.listagem(Entities.APLICACAO_ENTITY.getLabel()));
    }
    @GetMapping("/despesas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R200),
            @ApiResponse(responseCode = ResponseMessage.R204),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response listarDespesas(){
        return ResponseFactory.ok(service.listarDespesas(""),ResponseMessage.listagem(Entities.APLICACAO_ENTITY.getLabel()));
    } @GetMapping("/listagem/{area}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R200),
            @ApiResponse(responseCode = ResponseMessage.R204),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response listagens(@PathVariable("area")String area){
        return ResponseFactory.ok(service.listagem(area),ResponseMessage.listagem(Entities.APLICACAO_ENTITY.getLabel()));
    }
    @PostMapping("/areas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R201),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R401),
            @ApiResponse(responseCode = ResponseMessage.R403),
            @ApiResponse(responseCode = ResponseMessage.R409),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response incluirArea(@RequestBody AplicacaoRequest request){
        return ResponseFactory.create(service.incluirArea(request),ResponseMessage.inclusao(Entities.APLICACAO_ENTITY.getLabel()));
    }
    @PostMapping("/receitas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R201),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R401),
            @ApiResponse(responseCode = ResponseMessage.R403),
            @ApiResponse(responseCode = ResponseMessage.R409),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response incluirReceita(@RequestBody AplicacaoRequest request){
        return ResponseFactory.create(service.incluirReceita(request),ResponseMessage.inclusao(Entities.APLICACAO_ENTITY.getLabel()));
    }

    @PostMapping("/despesas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R201),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R401),
            @ApiResponse(responseCode = ResponseMessage.R403),
            @ApiResponse(responseCode = ResponseMessage.R409),
            @ApiResponse(responseCode = ResponseMessage.R500),
    })
    public Response incluirDespesa(@RequestBody AplicacaoRequest request){
        return ResponseFactory.create(service.incluirDespesa(request),ResponseMessage.inclusao(Entities.APLICACAO_ENTITY.getLabel()));
    }
    @PatchMapping("/{id}/nome/{nome}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseMessage.R200),
            @ApiResponse(responseCode = ResponseMessage.R400),
            @ApiResponse(responseCode = ResponseMessage.R401),
            @ApiResponse(responseCode = ResponseMessage.R404),
            @ApiResponse(responseCode = ResponseMessage.R409),
    })
    public Response alterarNome(@PathVariable("id") Integer id, @PathVariable("nome") String nome){
        return ResponseFactory.ok(service.alterarNome(id, nome),ResponseMessage.alteracao(Entities.APLICACAO_ENTITY.getLabel()));
    }

}
