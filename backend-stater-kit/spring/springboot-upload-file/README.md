# Digytal - Programação, Pesquisa e Educação
[www.digytal.com.br](http://www.digytal.com.br)

[(11) 95894-0362](https://api.whatsapp.com/send?phone=5511958940362)

#### Autores
- [Gleyson Sampaio](https://github.com/glysns)

## BackEnd - StarterKit - Spring Upload File - Api

Este projeto tem a finaliade de disponibilizar uma API para realizar upload e exibição de imagens salvas no disco ou em um banco de dados. O projeto foi baseado no exemplo [springboot-crud-api](https://github.com/glysns/backend-stater-kit/tree/main/spring/springboot-crud-api)

### Tecnologias

* Java 1.8+
* SpringBoot 2.7.4
* SpringDataJpa
* Hibernate
* SpringWeb
* Projeto Lombok
* H2 database
* Swagger OpenAPI

![image](https://github.com/glysns/backend-stater-kit/blob/main/spring/springboot-crud-api/src/main/resources/img/java-spring-framework.jpg)

#### Configurações adicionais

Vamos definir em nossa configuração um diretório padrão para armazenar as imagens recebidos via upload conforme abaixo:

#### **`application.properties`**
```shell
file.upload.folder=${PRODUCTION_FILE_UPLOAD_FOLDER:c:\\digytal}
```

> O H2 disponibiliza uma interface web para visualizar as tabelas e realizar operações básicas de SQL.

#### **`application.properties`**
```shell
spring.h2.console.enabled=true
```

Acesse o link : `http://localhost:8080/h2-console`

![image](https://github.com/glysns/backend-stater-kit/blob/main/spring/springboot-crud-api/src/main/resources/img/h2-login.png)


#### Refatoração

Para disponibilizar estes recursos com base no CRUD de um Cliente já existente foram realizados os procedimentos abaixo:

1. Alterar a entidade `model.cliente.ClienteEntity` para ter os atributos `photoPath` ou `photoByte`;
2. A classe `service.ClienteService` agora possui mais dois métodos: `changePhotoPath`e `changeEntityPhoto`
3. Criamos a classe `service.FileUploadService` para gerenciar as ações de upload e exibição de arquivos
4. Criamos mais um recurso `webservice.FileUploadResource` para tratar requisições relacionadas a upload de imgagens  


### Novas Operações

Abaixo temos novos recursos em nossa API

| Database | HTTP                  | Descrição                                                  |
|----------|-----------------------|------------------------------------------------------------|
| UPDATE   | PATCH:/clientes/photo-path/{id}| Adiciona o endereço da imagem no disco para o usuário localizado pelo ID|
| UPDATE   | PATCH:/clientes/photo-byte/{id}         | Adiciona os bytes da imagem no banco de dados para o usuário localizado pelo ID|
| SELECT   | GET:/upload-file/view-from-disk/{id}    | Busca o cliente pelo ID informado e retorna a imagem em disco - **MELHORAR**|
| SELECT   | GET:/upload-file/view-from-database/{id}    | Busca o cliente pelo ID informado e retorna o byte array da imagem salva no banco|
| NONE     | POST:/upload-file/save-on-disk    | Simplesmente salva a imagem no disco, ver `application.properties` |


![image](https://github.com/glysns/backend-stater-kit/blob/main/spring/springboot-crud-api/src/main/resources/img/swagger-cliente-photo.png)

![image](https://github.com/glysns/backend-stater-kit/blob/main/spring/springboot-crud-api/src/main/resources/img/img_path.png)

![image](https://github.com/glysns/backend-stater-kit/blob/main/spring/springboot-crud-api/src/main/resources/img/img_byte.png)



