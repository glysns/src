# Digytal - Programação, Pesquisa e Educação
[www.digytal.com.br](http://www.digytal.com.br)

[(11) 95894-0362](https://api.whatsapp.com/send?phone=5511958940362)


#### Autores
- [Gleyson Sampaio](https://github.com/glysns)

## BackEnd - StarterKit - Spring JWT Authentication - Api

Este projeto tem por finalidade implementar uma camada de autenticação utilizando Spring Security e JWT.

### Tecnologias

* Spring Security
* JWT

A autenticação é um processo de segurança para verificar a veracidade e autenticidade de uma pessoa ou objeto. O tema da autenticação de um indivíduo, entidade ou objeto não é algo novo, dado que a palavra "autêntico" vem do grego "authentikos", que significa "original, genuíno". Portanto, a autenticação é baseada na comparação e correspondência para determinar se algo ou alguém é, de fato, o que ou quem afirmam ser.

### JWT

O JSON Web Token é um padrão da Internet para a criação de dados com assinatura opcional e/ou criptografia cujo payload contém o JSON que afirma algum número de declarações. Os tokens são assinados usando um segredo privado ou uma chave pública/privada

### Infraestrutura

Vamos começar pela criação dos pacotes `infra.security`, `infra.security.jwt` que terão o papel de agrupar classes que representam configurações e meios de autenticação em nossa aplicação no JWT:

| Classe                           | Descrição                                                                                                                      |
|----------------------------------|--------------------------------------------------------------------------------------------------------------------------------|
| infra.security.Login             | Classe que representa os parâmetros de `username` e  `password` de um usuário válido                                           |
| infra.security.Session           | Classe que representa a sessão de um usuário contendo o `username` e o `token`                                                 |
| infra.security.jwt.JwtObject     | Classe que representará o jwt-token para ser convertido em Json e vice-versa.                                                  |
| infra.security.jwt.JwtFactory    | Classe responsável por criar o jwt-token em forma objeto e string                                                              |
| infra.security.jwt.JwtProperties | Classe que contém propriedades de `prefixo`, `chave privada` do token vindo do `application.properties` |
| infra.security.jwt.JwtFilter     | Classe que interceptará as requisições para converter e validar a estrutura do token recebido do Header.                       |
| infra.security.WebSecurityConfig | Classe que contém a configuração que define a estratégia de autenticação e consumo dos recursos da api                         |

### Configuração

#### **`pom.xml`**
```xml
<!-- Security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<!-- JWT -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.7.0</version>
</dependency>
```

#### **`application.properties`**
```shell
## Parametros de configuracao do Token
jwt.properties.prefix=Bearer
jwt.properties.key=MyKey
```

### Criando o token

Antes de pensar em consumir os recursos que agora estão seguros, precisaremos simular a criação de uma sessão com token com base em credencial `fake` passando o username e password. Ver implementação na classe `digytal.backend.api.webservice.LoginResource`.

De acordo com as novas configurações teremos um novo endpoint `POST:/public/login` para primeiro realizar autenticação conforme Json abaixo:

![image](https://github.com/glysns/backend-stater-kit/blob/main/spring/springboot-crud-api/src/main/resources/img/login.png)
```json
{
  "username": "gleyson",
  "password": "Jwt@123"
}
```

*Resposta - OK*
```json
{
    "dateTime": "2023-04-09 09:57:07",
    "success": true,
    "message": "Login realizado com sucesso",
    "code": "200",
    "body": {
    "username": "gleyson",
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnbGV5c29uIiwiaWF0IjoxNjgxMDQ1MDI3LCJleHAiOjE2ODEwNTk0MjcsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUl9BRE1JTiJdfQ.EncVeV-H1nm2ug9-lllGmVR_cK71u6yNZjaQJHyPzhBqEH9NMKeFlUGH_kaSr1J_DP3TRzV77_C7y1-dBL2oxA"
    },
    "suggestion": ""
}
```

*OU - Resposta - Erro*
```json
{
    "dateTime": "2023-04-09 09:59:29",
    "success": false,
    "message": "Usuário Inválido",
    "code": "403",
    "body": null,
    "suggestion": "Confirma seu usuário e senha"
}
```

### Gran finale

Agora precisaremos dizer ao swagger que o mesmo deverá possibilitar incluir o token que foi gerado na chamada da requisição de login, ver configuração na classe `infra.doc.OpenAPI30Configuration`

![image](https://github.com/glysns/backend-stater-kit/blob/main/spring/springboot-crud-api/src/main/resources/img/token.png)

Cole o token gerado anteriormente: `Bearer eyJhbGciOiJIUz ...`

SMARTCONTRACT