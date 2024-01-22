# Digytal - Programação, Pesquisa e Educação
[www.digytal.com.br](http://www.digytal.com.br)

[(11) 95894-0362](https://api.whatsapp.com/send?phone=5511958940362)


#### Autores
- [Gleyson Sampaio](https://github.com/glysns)

## BackEnd - StarterKit - Spring JWT Database Authentication - Api

Este projeto tem por finalidade implementar uma camada de autenticação utilizando Spring Security e JWT obtendo dados usuário armazenado em um banco de dados. Ver projeto inicial em [springboot-jwt-authentication](https://github.com/glysns/backend-stater-kit/tree/main/spring/springboot-jwt-authentication).

### Tecnologias

* Spring Security
* JWT
* Password Encoder
* PostgreSQL


### Refatoração
Com base no projeto anterior `springboot-jwt-authentication`, precisamos agora realizar alguns procedimentos:

* Criar a entidade que representará o usuário que será armazenado no banco de dados;
* Criar um novo script.sql que realizará a criação da tabela de usuário e a inseração de um usuário padrão, ver `V01_03__create_tab_usuario.sql`;
* Criar uma classe utilitária que consiga criptografar uma senha e depois compará-la no momento do login, ver `PasswordEncoder`
* Refatorar a operação de login de forma que agora os dados de usuário e senha estejam localizadas no banco de dados.


1. Usuário

Nossa primeira missão será criar a nossa entidade que representará um usuário no sistema com seu respectivo `dto`, `request` e `repository`, ver classes do pacote `model.usuario`.


2. Gravar Usuário

Precisaremos agora gravar o nosso usuário considerando que a senha será primeiramente criptograda e salva no banco de dados. Vamos usar uma classe de criptografia disponibilizada pelo String e configurada na classe `infra.security.WebSecurityConfig` através da implementação desenvolvida na nova classe `service.UsuarioService` e seu controller `webservice.UsuarioResource`.

3. Refatorando o Login

Agora para realizar o login, o nosso usuário será localizado na base de dados e comparado a senha que foi armazenada anteriormente, ver classe `webservice.LoginResource`





