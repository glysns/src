create table tab_cliente
(
    id            serial
        primary key,
    cpf           varchar(15)      not null,
    dt_nascimento date             not null,
    nome          varchar(50)      not null,
    renda_mensal  double precision not null,
    sexo          varchar(10)      not null
);
