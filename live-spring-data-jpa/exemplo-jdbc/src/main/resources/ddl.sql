-- public.tab_profissao definition

-- Drop table

-- DROP TABLE public.tab_profissao;

CREATE TABLE public.tab_profissao (
	id          serial4         NOT NULL,
	nome        varchar(255)    NOT NULL,
	CONSTRAINT tab_profissao_pkey PRIMARY KEY (id)
);

--insert into tab_profissao (nome) values ('INSTRUTOR');