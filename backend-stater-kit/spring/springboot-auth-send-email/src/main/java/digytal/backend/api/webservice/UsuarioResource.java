package digytal.backend.api.webservice;

import digytal.backend.api.infra.handler.Response;
import digytal.backend.api.infra.handler.ResponseFactory;
import digytal.backend.api.model.usuario.UsuarioNovaSenha;
import digytal.backend.api.model.usuario.UsuarioRequest;
import digytal.backend.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class UsuarioResource {
    @Autowired
    private UsuarioService service;
    @PostMapping("/usuarios")
    public Response save(@RequestBody UsuarioRequest request){
        service.gravarUsuario(request);
        return ResponseFactory.ok(true,"Usuário salvo com sucesso mas revise as validações de cadastro de usuário");
    }
    @PatchMapping("/usuarios/esqueci-senha/{email}")
    public Response gerarSenhaTemporaria(@PathVariable("email") String email){
        service.gerarSenhaTemporaria(email);
        return ResponseFactory.ok(true,"Senha temporária gerada com sucesso, confirme sua caixa de e-mail");
    }
    @PatchMapping("/usuarios/altera-senha")
    public Response alterarSenha(@RequestBody UsuarioNovaSenha request){
        service.alterarSenha(request);
        return ResponseFactory.ok(true,"Senha alterada com sucesso");
    }
}
