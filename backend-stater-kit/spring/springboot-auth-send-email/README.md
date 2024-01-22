# Digytal - Programação, Pesquisa e Educação
[www.digytal.com.br](http://www.digytal.com.br)

[(11) 95894-0362](https://api.whatsapp.com/send?phone=5511958940362)


#### Autores
- [Gleyson Sampaio](https://github.com/glysns)

## BackEnd - StarterKit - Spring Send Email - Api

Este projeto tem por finalidade implementar o envio de e-mail contendo a senha padrão dos usuários do sistemas. Ver projeto inicial em [springboot-jwt-database-roles-authentication](https://github.com/glysns/backend-stater-kit/tree/main/spring/springboot-jwt-database-roles-authentication).

### Tecnologias

* Spring Security
* Spring Email
* G-Mail

## Configuração

Habilitar o envio de e-mail pelo SpringBoot integrando ao G-Mail hoje é uma tarefa bem simples, siga as instruções abaixo:

1. Adicione a dependência (starter) do `spring-boot-starter-mail`

#### **`pom.xml`**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```

2. Configure as propriedades para envio de e-mail

#### **`application.properties`**
```shell
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=gleyson@digytal.com.br
spring.mail.password=${EMAIL_PASSWORD}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.smtp.ssl.enable=false
spring.mail.test-connection=false
```

Observe que na configuração o parâmetro `spring.mail.password` espera uma variável chamada de `${EMAIL_PASSWORD}`, quando for executar sua aplicação, você definirá esta variável utilizando sua IDE ou informando como variáveis de ambiente no Spring.

>Duas observações em relação a configuração acima, primeira seu e-mail e senha **NÃO PODEM** estar diretamente no `application.properties` e sim na configuração de seu servidor ou container e o G-Mail recomanda **NÃO USAR** sua senha pessoal, mas sim criar uma senha para esta finalidade em específica.

3. G-Mail senhas adicionais

O serviço de envio de Google tem o recurso de adicionar senhas adicionais para finalidades específicas de uso (Senhas de App), no nosso caso, envio de e-mail através da nossa API, veja tutorial no link [movidesk](https://atendimento.movidesk.com/kb/article/280320/configurar-senha-app-google-movidesk).

## Refatoração

Agora o nosso usuário terá mais um atributo senha aonde será utilizada para enviar a senha temporária ou quando o mesmo necessitar resetar a senha.

**Novos recursos**

Precisaremos configurar um objeto que representará um transmissor de e-mail e lembrando que em Java tudo é objeto, logo, o e-mail será um objeto.

1. Criando novo recurso disponível em `infra.email`, ver classe `SendEmailService`.

**Alterações**

Esta jornada será composta por 2 etapas, a primeira agora o usuário receberá uma senha temporário e após poderá alterar a senha passando seu e-mail, senha atual e a nova senha.

1. Criada a migrations `V01_05__alter_tab_usuario_add_email.sql`;
2. Alterar as classes em `model.usuario` para contemplar o novo campo `email`;
3. Adicionar o método no `UsuarioRepository` que possa localizar o usuário pelo e-mail;
4. Implementar na classe `UsuarioService` enviar e-mail ao criar um novo usuário
5. Implementar na classe `UsuarioService` um novo recurso de esqueci a senha e alterar a senha.

![IMG](https://github.com/glysns/backend-stater-kit/blob/main/spring/springboot-crud-api/src/main/resources/img/user-login.png)
