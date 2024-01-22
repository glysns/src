package com.digytal.control.webservice.reports;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report/pagamentos")
@Tag(name = "Recursos referente a report/pagamentos")
public class PagamentoReportResource extends ReportResource {
    /*
    @Autowired
    private PagamentoConsultaService service;
    @GetMapping(value = "/resumo/{dataInicial}/{dataFinal}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> exibirResumoPagamentos(@PathVariable("dataInicial") LocalDate dataInicial,@PathVariable("dataFinal") LocalDate dataFinal) throws Exception{
        Object records= service.gerarResumo(dataInicial,dataFinal);
        return exibirRelatorio("pagamento-resumo", records,"pagamento-detalhe-sub","pagamento-detalhe-sub","pagamento-resumo-contas");
    }
    
    @GetMapping(value = "/json/{dataInicial}/{dataFinal}")
    public PagamentoResumo reumo(@PathVariable("dataInicial") LocalDate dataInicial,@PathVariable("dataFinal") LocalDate dataFinal) throws Exception{
    	PagamentoResumo records= service.gerarResumo(dataInicial,dataFinal);
        return records;
    }

     */
}
