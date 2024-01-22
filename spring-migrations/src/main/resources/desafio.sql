1. Os comandos SQL são classificados em sub-linguagens conhecidas como?

a) DDL, DML, DQL, DTL, DCL
b) DDL, DAL, DXL, DVL, DCL
C) DML, DTL, DXL, DVL, DCL
d) DQL, DML, DXL, DTL, DCL

2. Quais os comandos compoem a sub-linguagem DML?

a) INSERT, SELECT, UPDATE, DELETE
b) INSERT, UPDATE, DELETE
C) SELECT, FROM, WHERE
d) BEGIN, TRANSACTION, COMMIT, ROLLBACK

3. Qual o objetivo da sub linguagem DQL?

a) Responsável pela definição dos elementos no banco de dados
b) Responsável pela manipulação dos registeos no banco de dados
C) Responsável pela consulta dos registeos no banco de dados
d) Responsável pela segurança dos registeos no banco de dados

4. Qual sigla abaixo representa os quatro pilares necessários para a realização de uma transação em um sistema de banco de dados?

a) SGBD
b) ACAC
C) ACID
d) DBCD

5. Qual dos itens abaixo NÃO faz parte dos quatro pilares necessários para a realização de uma transação em um sistema de banco de dados?

a) Atomicidade
b) Compliance
C) Isolamento
d) Durabilidade

6. Qual comando sql abaixo é MAIS adequado para criar a tabela cidade com os campos: nome e uf (sigla do estado) onde ambos são obrigatórios?

a) create table cidade (nome varchar(80), uf varchar (2));
b) create table cidade (nome varchar not null, uf char (2) not null);
C) create table cidade (nome varchar(80) not null, uf varchar (2) not null);
d) create table cidade (nome varchar(80) not null, uf char (2) not null);

Justifique sua resposta:

7. Qual o comando abaixo possui a mesma finalidade de execução que o comando DELETE?

a) REMOVE
b) DROP RECORDS
c) TRUNCATE
d) DUMP

8. Imagina que você foi designado a criar uma ação em uma tabela que atualizará o registro de outra tabela no momento da inserção de um novo registro,
qual recurso é utilizado em banco de dados para proporcionar este comportamento?

a) Store de Procedure
b) Function
c) Trigger
d) Job

9. Considerando a questão 6 e os inserts abaixo, elabore um SELECT onde seja possível contar as cidades do uf igual a PI

INSERT INTO public.cidade (nome, uf) VALUES('Teresina', 'PI');
INSERT INTO public.cidade (nome, uf) VALUES('Sao Paulo', 'SP');
INSERT INTO public.cidade (nome, uf) VALUES('Santa Ines', 'MA');
INSERT INTO public.cidade (nome, uf) VALUES('Picos', 'PI');

10. Considerando a questão 9, agora elabore uma instrução sql para que seja possível realizar os procedimentos abaixo:

1. criar o novo campo chamado população do tipo numerico inteiro
2. definir os valores de população para: Teresina = 200.456, Sao Paulo = 1237.126, demais (nulos) = 0;
3. listar as cidades por ordem de população com ordem decrescente (do maior para o menor)

