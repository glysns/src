package digytal.backend.api.webservice;

import digytal.backend.api.infra.handler.Response;
import digytal.backend.api.infra.handler.ResponseFactory;
import digytal.backend.api.model.usuario.UsuarioRequest;
import digytal.backend.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
