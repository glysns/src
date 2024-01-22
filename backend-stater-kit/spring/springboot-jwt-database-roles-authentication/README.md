# Digytal - Programação, Pesquisa e Educação
[www.digytal.com.br](http://www.digytal.com.br)

[(11) 95894-0362](https://api.whatsapp.com/send?phone=5511958940362)


#### Autores
- [Gleyson Sampaio](https://github.com/glysns)

## BackEnd - StarterKit - Spring JWT Database Roles Authentication - Api

Este projeto tem por finalidade implementar uma camada de autenticação utilizando Spring Security e JWT obtendo dados usuário armazenado em um banco de dados e aplicando regras de perfis de acesso (roles). Ver projeto inicial em [springboot-jwt-database-authentication](https://github.com/glysns/backend-stater-kit/tree/main/spring/springboot-jwt-database-authentication).

### Tecnologias

* Spring Security
* JWT
* Password Encoder
* PostgreSQL
* Perfil de Acesso (Roles)

### Autenticação

A autenticação verifica a identidade digital do usuário, ou seja, processo de verificação de uma identidade. Em termos mais simples, é quando o usuário prova de fato quem ele é.

Um exemplo bem comum de autenticação é a combinação Username e Password (Usuário e senha).

### Autorização

Por sua vez, a autorização é o processo que ocorre após ser validada a autenticação. Diz respeito aos privilégios que são concedidos a determinado usuário ao utilizar uma aplicação.

Serve para verificar se determinado usuário terá a permissão para utilizar, executar recursos ou manipular determinadas ações, que é de fundamental importância dentro de uma aplicação.

Um exemplo que podemos atribuir a autorização é o uso de um ERP de uma determinada empresa.

Após realizar a autenticação no sistema, o usuário do financeiro terá acesso apenas aos módulos correspondentes à realização de seu trabalho, como contas a pagar, contas a receber, etc.

### Roles

Role é uma definição de perfil ou grupo de usuário que determina os recursos autorizados para serem acessados por um usuário.

No contexto iremos utilizar dois usuarios com os repectivos perfís (role):

```
username = user,password = UserP@ss, role = USER

username = admin, password = M@nager, role = MANAGER
```

>Ver nova migration `V01_04__alter_tab_usuario_add_role.sql`

### Refatoração

Foram realizados alguns ajustes em nossa aplicação para considerar a verificação do perfil do usuário.

* Nossa classe `model.usuario.UsuarioEntity` terá um novo atributo denominado de `role`;
* Na classe `api.webservice.LoginResource` agora ao gerar o token pegamos o perfil do usuário de acordo com o cadastro, linha 46.

Ao realizar um novo login você poderá analisar o token que foi gerado utilizando o site https://jwt.io/.

### Autorização

Quanto realizamos o login com sucesso nos autenticamos em nossa API através do token que foi gerado, mas será se o meu perfil terá permissão de acessar todos os recursos disponíveis?

Vamos imaginar que, somente um usuário com perfil (role) `MANAGER` poderá realizar a exclusão de um cliente?

Veja a alteração realizada na classe `infra.security.WebSecurityConfig`.


```java
.antMatchers(HttpMethod.DELETE,"/clientes/**").hasAnyRole("MANAGER")
                
.antMatchers(HttpMethod.POST,"/clientes").hasAnyRole("MANAGER","USER")
.antMatchers(HttpMethod.GET,"/clientes").hasAnyRole("MANAGER","USER")
.antMatchers(HttpMethod.PUT,"/clientes").hasAnyRole("MANAGER","USER")
```

**NOTA:** Ao tentar excluir um cliente com o token gerado de um usuário perfil `USER`, você não deverá ter permissão.

```
403 Forbidden (Proibido) 
```

>Para casos que necessitem de um contexto mais avançado é super válido alinhar uma melhor estratégia envolvendo todo o time.

**Referências**

https://www.treinaweb.com.br/blog/autenticacao-x-autorizacao
