package com.digytal.control.webservice.publico;


import com.digytal.control.infra.http.response.Response;
import com.digytal.control.infra.http.response.ResponseFactory;
import com.digytal.control.infra.model.CredenciamentoResponse;
import com.digytal.control.infra.model.LoginRequest;
import com.digytal.control.model.modulo.acesso.usuario.SenhaAlteracaoRequest;
import com.digytal.control.model.comum.cadastramento.CadastroSimplificadoRequest;
import com.digytal.control.service.modulo.acesso.EmpresaService;
import com.digytal.control.service.modulo.acesso.LoginService;
import com.digytal.control.service.modulo.acesso.PrimeiroAcessoService;
import com.digytal.control.service.modulo.acesso.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Tag(name = "Recursos publico")
public class PublicoResource {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PrimeiroAcessoService primeiroAcessoService;
    @Autowired
    private LoginService loginService;

    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/empresa/primeiro-acesso/{cpfCnpj}")
    public Response realizarPrimeiroAcessoEmpresa(@PathVariable("cpfCnpj") String cpfCnpj, @RequestBody CadastroSimplificadoRequest request){
        CredenciamentoResponse response = primeiroAcessoService.configurarPrimeiroAcesso(cpfCnpj, request);
        return ResponseFactory.ok(response,"Primeiro acesso realizado com sucesso, confira sua caixa de e-mail");
    }
    @PatchMapping("/alteracao-senha/{expiracao}")
    public Response alterarSenhaExpirada(@PathVariable("expiracao")Long expiracao, @RequestBody SenhaAlteracaoRequest request){
        return ResponseFactory.ok(usuarioService.alterarSenha(expiracao, request),"Senha alterada com sucesso");
    }
    @PostMapping("/login")
    public Response logar(@RequestBody LoginRequest login){
        return ResponseFactory.ok(loginService.autenticar(login),"Login realizado com sucesso");
    }

    @GetMapping("/empresas/selecao/{empresa}")
    public Response selecionarEmpresa(@PathVariable("empresa") Integer empresa, @RequestHeader("Authorization") String token){
        return ResponseFactory.ok(empresaService.selecionarEmpresa(empresa, token),"Token atualizado");
    }

    @PatchMapping("/solicitacao-nova-senha/id/{id}")
    public Response solicitarNovaSenha(@PathVariable("id")Integer id){
        return ResponseFactory.ok(usuarioService.solicitarNovaSenha(id),"Solicitação de nova senha realizada com sucesso");
    }
    @PatchMapping("/solicitacao-nova-senha/login/{login}")
    public Response solicitarNovaSenha(@PathVariable("login")String login){
        return ResponseFactory.ok(usuarioService.solicitarNovaSenha(login),"Solicitação de nova senha realizada com sucesso");
    }
}
