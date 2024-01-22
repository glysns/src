package com.digytal.control.webservice.modulo.param;

import com.digytal.control.infra.http.response.Response;
import com.digytal.control.infra.http.response.ResponseFactory;
import com.digytal.control.service.modulo.param.BancoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bancos")
@Tag(name = "Recursos referente ao banco")
public class BancoResource {
    @Autowired
    private BancoService service;
    @GetMapping("/{nome}")
    public Response obterCep(@PathVariable("nome") String nome){
        return ResponseFactory.ok(service.listar(nome),"Consulta realizada com sucesso");
    }
}
