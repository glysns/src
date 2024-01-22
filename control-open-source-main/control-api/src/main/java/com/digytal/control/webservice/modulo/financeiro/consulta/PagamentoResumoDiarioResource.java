package com.digytal.control.webservice.modulo.financeiro.consulta;

import com.digytal.control.infra.http.response.Response;
import com.digytal.control.infra.http.response.ResponseFactory;
import com.digytal.control.model.consulta.lancamento.PagamentoFiltro;
import com.digytal.control.service.modulo.financeiro.consulta.PagamentoConsultaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumo-diario")
@Tag(name = "Recursos referente aos laçamentos diário da empresa")
public class PagamentoResumoDiarioResource {
    @Autowired
    private PagamentoConsultaService service;
    @GetMapping()
    public Response exibirResumoDiario(){
        return ResponseFactory.ok(service.resumir(new PagamentoFiltro()),"Consulta realizada com sucesso!");
    }
}
