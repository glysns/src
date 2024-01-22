package com.digytal.control.webservice.modulo.acesso;

import com.digytal.control.infra.http.response.Response;
import com.digytal.control.infra.http.response.ResponseFactory;
import com.digytal.control.model.modulo.acesso.empresa.EmpresaRequest;
import com.digytal.control.service.modulo.acesso.EmpresaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresas")
@Tag(name = "Recursos referente a empresas")
public class EmpresaResource {
    @Autowired
    private EmpresaService service;
    @GetMapping("/vinculadas")
    public Response logar(){
        return ResponseFactory.ok(service.listarUsuarioEmpresas(),"Consulta realizada com sucesso");
    }
    @GetMapping("/{id}")
    public Response buscar(@PathVariable("id") Integer id){
        return ResponseFactory.ok(service.buscar(id),"Pesquisa realizada com sucesso");
    }
    @PutMapping("/{id}")
    public Response alterarEmpresa(@PathVariable("id") Integer id, @RequestBody EmpresaRequest request){
        return ResponseFactory.ok(service.alterar(id, request),"Empresa alterada com sucesso");
    }
    @PostMapping()
    @ResponseStatus( HttpStatus.CREATED )
    public Response incluirEmpresa(@RequestBody EmpresaRequest request){
        return ResponseFactory.create(service.incluir(request),"Empresa registrada com sucesso");
    }
    @PatchMapping("/asaas/token/{token}")
    public Response alterarAsaasToken(@PathVariable("token") String token){
        service.alterarAsaasToken(token);
        return ResponseFactory.ok(true,"Alteração do Token realizada com sucesso");
    }
    @PatchMapping("/asaas/webhook-token/{token}")
    public Response alterarAsaasWebHookToken(@PathVariable("token") String token){
        service.alterarAsaasWebhookToken(token);
        return ResponseFactory.ok(true,"Alteração do WebHookToken realizada com sucesso");
    }
    @PatchMapping("/asaas/taxa-boleto/{taxa}")
    public Response alterarAsaasTaxaBoleto(@PathVariable("taxa") Double taxa){
        service.alterarAsaasTaxaBoleto(taxa);
        return ResponseFactory.ok(true,"Alteração da taxa de boleto realizada com sucesso");
    }
}
