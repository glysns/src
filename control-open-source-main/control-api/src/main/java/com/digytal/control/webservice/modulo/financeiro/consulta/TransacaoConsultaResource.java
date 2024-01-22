package com.digytal.control.webservice.modulo.financeiro.consulta;

import com.digytal.control.infra.http.response.Response;
import com.digytal.control.infra.http.response.ResponseFactory;
import com.digytal.control.model.consulta.lancamento.PagamentoFiltro;
import com.digytal.control.model.consulta.lancamento.TransacaoFiltro;
import com.digytal.control.service.modulo.financeiro.TransacaoService;
import com.digytal.control.service.modulo.financeiro.consulta.PagamentoConsultaService;
import com.digytal.control.service.modulo.financeiro.consulta.TransacaoConsultaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/consultas/transacoes")
@Tag(name = "Recursos referente a consulta de transações")
public class TransacaoConsultaResource {
    @Autowired
    private TransacaoConsultaService service;
    @GetMapping()
    public Response pesquisar(TransacaoFiltro filtro ){
        return ResponseFactory.ok(service.pesquisar(filtro),"Pesquisa realizada com sucesso!");
    }
    @GetMapping("/completa")
    public Response pesquisarCompleta(TransacaoFiltro filtro ){
        return ResponseFactory.ok(service.pesquisarCompleto(filtro),"Pesquisa realizada com sucesso!");
    }
}
