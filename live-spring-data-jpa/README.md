-- DROP TABLE tab_profissao;

CREATE TABLE tab_profissao (
	id serial4 NOT NULL,
	nome varchar(70) not NULL,
	CONSTRAINT pk_tab_profissao PRIMARY KEY (id)
);