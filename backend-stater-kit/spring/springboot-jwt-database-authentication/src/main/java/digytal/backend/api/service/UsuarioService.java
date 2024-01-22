package digytal.backend.api.service;

import digytal.backend.api.model.usuario.UsuarioEntity;
import digytal.backend.api.model.usuario.UsuarioRequest;
import digytal.backend.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public void gravarUsuario(UsuarioRequest request){
        UsuarioEntity entity = new UsuarioEntity();

        entity.setNome(request.getNome());
        entity.setLogin(request.getLogin());

        String senhaCriptografada = encoder.encode(request.getSenha());
        entity.setSenha(senhaCriptografada);

        repository.save(entity);
    }
}
