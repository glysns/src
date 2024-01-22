package com.digytal.control.webservice.modulo.financeiro;


import com.digytal.control.infra.http.response.Response;
import com.digytal.control.infra.http.response.ResponseFactory;
import com.digytal.control.integracao.asaas.model.BoletoResponse;
import com.digytal.control.model.modulo.financeiro.parcelamento.parcela.liquidacao.ParcelaPagamentoRequest;
import com.digytal.control.service.modulo.financeiro.BoletoService;
import com.digytal.control.service.modulo.financeiro.ParcelamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoResource {
    @Autowired
    private ParcelamentoService service;
    @Autowired
    private BoletoService boletoService;

    @PostMapping("/parcelas/{id}/pagamento")
    @ResponseStatus( HttpStatus.CREATED )
    public Response realizarCompensacao(@PathVariable("id") Integer id, @RequestBody List<ParcelaPagamentoRequest> request){
        service.realizarCompensacao(id,request);
        return ResponseFactory.create(true,"Pagamento realizado com sucesso");
    }
    @PatchMapping("/parcelas/{parcela}/valor/{valorBoleto}/boleto")
    public Response gerarBoleto(@PathVariable("parcela") Integer parcela, @PathVariable("valorBoleto") Double valorBoleto){
        BoletoResponse response = boletoService.gerarBoleto(parcela, valorBoleto);
        return ResponseFactory.create(response,"Solicitação realizada com sucesso");
    }
    @PatchMapping("/parcelas/{parcela}/pagamento-boleto")
    public Response compensarBoleto(@PathVariable("parcela") Integer parcela){
        boletoService.compensar(parcela);
        return ResponseFactory.ok(true,"Solicitação de compensação de pagamento realizada com sucesso");
    }

}
