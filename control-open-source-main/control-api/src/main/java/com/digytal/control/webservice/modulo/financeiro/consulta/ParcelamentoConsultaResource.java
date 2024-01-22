package com.digytal.control.webservice.modulo.financeiro.consulta;

import com.digytal.control.infra.http.response.Response;
import com.digytal.control.infra.http.response.ResponseFactory;
import com.digytal.control.model.consulta.lancamento.LancamentoFiltro;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import com.digytal.control.service.modulo.financeiro.consulta.ParcelamentoConsultaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas/parcelamentos")
@Tag(name = "Recursos referente a consultas de parcelamentos")
public class ParcelamentoConsultaResource {
    @Autowired
    private ParcelamentoConsultaService service;
    @GetMapping
    public Response pesquisar(LancamentoFiltro filtro ){
        return ResponseFactory.ok(service.pesquisar(filtro),"Pesquisa realizada com sucesso!");
    }
    @GetMapping("/receitas")
    public Response pesquisarReceitas(LancamentoFiltro filtro ){
        filtro.setTipo(AplicacaoTipo.RECEITA);
        return ResponseFactory.ok(service.pesquisar(filtro),"Pesquisa realizada com sucesso!");
    }
    @GetMapping("/despesas")
    public Response pesquisarDespesas(LancamentoFiltro filtro ){
        filtro.setTipo(AplicacaoTipo.DESPESA);
        return ResponseFactory.ok(service.pesquisar(filtro),"Pesquisa realizada com sucesso!");
    }
    @GetMapping("/{parcelamento}/parcelas")
    public Response consultarPacelas(@PathVariable("parcelamento") Integer parcelamento){
        return ResponseFactory.ok(service.listarParcelas(parcelamento),"Consulta realizada com sucesso!");
    }
}
