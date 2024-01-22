create table tab_usuario
(
    id            serial primary key,
    nome          varchar(50)      not null,
    login         varchar(20)      not null,
    senha         varchar(100)     not null
);

insert into tab_usuario (nome, login, senha)
values ('GLEYSON SAMPAIO','gleyson','$2a$10$YAcQ2rAN8TjoXTFCKIKww.3hCmwKh1.v1s8AJV5XrAgR.3rVvZPrK');
