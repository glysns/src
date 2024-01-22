package digytal.backend.api.service;

import digytal.backend.api.infra.email.Message;
import digytal.backend.api.infra.email.SendEmail;
import digytal.backend.api.model.usuario.UsuarioEntity;
import digytal.backend.api.model.usuario.UsuarioNovaSenha;
import digytal.backend.api.model.usuario.UsuarioRequest;
import digytal.backend.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UsuarioService {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private SendEmail sendEmail;

    @Transactional
    public void gravarUsuario(UsuarioRequest request){
        /**
         * FAÇA AS VALIDAÇÕES NECESSÁRIAS
         */
        UsuarioEntity entity = new UsuarioEntity();

        entity.setNome(request.getNome());
        entity.setLogin(request.getLogin());

        //uma senha temporária de 8 digitos
        String senhaCriptografada =  UUID.randomUUID().toString().substring(0, 8);
        entity.setSenha(encoder.encode(senhaCriptografada));
        entity.setRole("USER");
        entity.setEmail(request.getEmail());
        repository.save(entity);

        Message m = new Message();
        m.setTo(request.getEmail());
        m.setTitle("Digytal APP - E-mail de criação de usuário");
        String msg = String.format("Olá %s,\nSeja bem-vindo a nossa plataforma online , " +
                " para acessar os recusos online basta informar seu login e senha conforme abaixo.\n\nLogin: %s\nSenha: %s\n\nAtenciosamente,\nDigytal", request.getNome(), request.getLogin(), senhaCriptografada);
        m.setBody(msg);
        sendEmail.send(m);
    }
    @Transactional
    public void gerarSenhaTemporaria(String email){
        //localizando o usuario pelo login
        UsuarioEntity entity = repository.findByEmail(email);
        /**
         * FAÇA AS VALIDAÇÕES NECESSÁRIAS
         */
        //uma senha temporária de 8 digitos
        String senhaCriptografada =  UUID.randomUUID().toString().substring(0, 8);
        System.out.println("***"+senhaCriptografada+"***");
        entity.setSenha(encoder.encode(senhaCriptografada));

        repository.save(entity);

        Message m = new Message();
        m.setTo(entity.getEmail());
        m.setTitle("Digytal APP - Reset de Senha");
        String msg = String.format("Olá %s,\nSegue nova senha temporária para que você possa voltar a acessar a nossa plataforma.\n\n " +
                "Senha temporária: %s\n\nAtenciosamente,\nDigytal", entity.getNome(), senhaCriptografada);
        m.setBody(msg);
        sendEmail.send(m);
    }
    @Transactional
    public void alterarSenha(UsuarioNovaSenha novaSenha){
        //localizando o usuario pelo login
        UsuarioEntity entity = repository.findByEmail(novaSenha.getEmail());

        /**
         * FAÇA AS VALIDAÇÕES PARA ALTERAÇÃO DE SENHA
         */

        //uma senha temporária de 8 digitos
        String senhaCriptografada =  novaSenha.getSenhaNova();
        entity.setSenha(encoder.encode(senhaCriptografada));

        repository.save(entity);
    }
}
