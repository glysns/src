# Digytal Code - Programação, Pesquisa e Educação
[www.digytal.com.br](http://www.digytal.com.br)

[(11) 95894-0362](https://api.whatsapp.com/send?phone=5511958940362)


#### Autores
- [Gleyson Sampaio](https://github.com/glysns)

## BackEnd - StarterKit - Spring Rest API - Exception Handler

Este projeto é uma evolução do projeto [springboot-crud-postgres-flyway-api](https://github.com/glysns/backend-stater-kit/tree/main/spring/springboot-crud-postgres-flyway-api) para ilustrar o tratamento de exceções como respostas customizadas para o usuário em requisições HTTP Rest no formato JSON.

### Tecnologias

* Spring Web
* Global Exception Handlers

Sabemos que exceções são comportamentos inesperados em nossa aplicação, muitas destas exceções estão relacionadas uma interação "indevida" do usuário, exemplo:

* Não informar o nome do cliente
* O CPF informado não possui 11 digitos ou não é valido mediante as regras definidas (ver regex)
* A data de nascimento é superior a data de hoje
* Entre outros cenários

>Então como implementar uma estrutura que suporte uma padronização de uma resposta ao usuário em casos inesperados? **Global Exception Handlers**.

### Infraestrutura

Vamos começar pela criação dos pacotes `infra`,`infra.handler`, `infra.hanlder.exceptions` que terá o papel de agrupar classes que representam configurações em nosso projeto e em seguida criar as classes conforme propostas abaixo:

| Classe                                     | Descrição                                                                                    |
|--------------------------------------------|----------------------------------------------------------------------------------------------|
| infra.handler.GlobalExceptionHandler       | Classe que contém a configuração global que interceptará as nossas exceções de negócio.      |
| infra.handler.exceptions.BusinessException | Classe que representa a mãe de todos os tipos de exceções de negócio da aplicação.           |
| infra.handler.Response                     | Classe que representará as respostas de sucesso ou exceção diante das requisições do usuário |
| infra.handler.ResponseFactory              | Classe que auxilia na criação das exceções de negócio como uma `Response`                      |

> BusinessException ou Exceções de Negócio são classes que representam as exceções que uma aplicação poderá apresentar de acordo com interação do usuário.

Classes que sofreram alteração (refatoração): `ClienteService` e `ClienteResource`.

Ao realizar operações de sucesso você deverá agora receber a resposta Json abaixo:

```json
{
  "dateTime": "2023-04-09 07:31:26",
  "success": true,
  "message": "Cliente salvo com sucesso",
  "code": "200",
  "body": 2,
  "suggestion": ""
}
```
| Campo    | Descrição                                                  |
|----------|------------------------------------------------------------|
| dataTime | Data e hora da resposta                                    |
| success  | Requisição realizada com sucesso                           |
| message  | Mensagem de resposta                                       |
| code     | Código de status                                           |
| body     | Corpo da resposta podendo ser um ID, um Objeto ou um Array |
| suggestion  | Uma sugestão em caso de erro na requisição                 |


### Exceções implementadas

Tente realizar as operações abaixo para analisar as respostas das requisições de forma que se espera uma exceção de negócio.

1. Localizar um cliente pelo `ID` inexistente na base de dados
2. Tentar salvar ou alterar um cliente sem informar os campos `nome` e `cpf`


>Como se tratam de exceções de negócio foi definido o http status code como `409 - CONFLICT`  


```json
{
  "dateTime": "2023-04-09 07:40:53",
  "success": false,
  "message": "Campo obrigatório: Nome",
  "code": "101",
  "body": null,
  "suggestion": "Preencha o campo obrigatório"
}
```