package com.digytal.control.webservice.reports;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.digytal.control.infra.config.RequestInfo;
import com.digytal.control.infra.report.ReportGenerator;
import com.digytal.control.service.modulo.acesso.EmpresaService;

//https://www.tutorialspoint.com/jasper_reports/jasper_report_parameters.htm
@Component
public abstract class ReportResource {
	public static final String EMPRESA_CABECALHO = "EMPRESA_CABECALHO";
	@Autowired
	private EmpresaService empresaService;
	@Autowired
	private RequestInfo requestInfo;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
	public ResponseEntity exibirRelatorio (String nomeRelatorio, Object registros) throws Exception{
		return exibirRelatorio(nomeRelatorio, registros, null);
	}
	public ResponseEntity exibirRelatorio (String nomeRelatorio, Object registros, String ... subReports) throws Exception{
		LocalDateTime dataHora = LocalDateTime.now();
		String horario = dataHora.format(formatter);
		
		ReportGenerator generator = ReportGenerator.of(nomeRelatorio+".jasper");
		generator.setParameter(EMPRESA_CABECALHO, empresaService.gerarCabecalho(requestInfo.getEmpresa()));
		if(subReports!=null)
			generator.setSubReports(subReports);
        generator.setData(registros);
        
        byte[] content= generator.generateStream();
        
        ByteArrayInputStream fis = new ByteArrayInputStream(content);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("inline; filename=%s-%s.pdf", nomeRelatorio, horario) );
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(fis));
	}

}
