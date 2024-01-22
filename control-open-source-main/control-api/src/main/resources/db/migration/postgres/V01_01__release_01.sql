CREATE SCHEMA apl_param;

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- MÓDULO : apl_param						DESCRIÇÃO: Módulo contendo tabelas de parametrizações do sistema
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE apl_param.tab_ibge (
	id                          int4                            NOT NULL,
	nome 					    varchar(100) 		            NOT NULL,
	sigla 					    varchar(10) 		            NOT NULL,
	uf_id 					    int4 				            NOT NULL,
	uf_nome 				    varchar(50) 		            NOT NULL,
	uf_sigla 				    varchar(4) 			            NOT NULL,
	prioridade 				    int4 				            NOT NULL,
	nome_oficial 			    varchar(100) 		            NOT NULL,
	CONSTRAINT pk_params_ibge                                   PRIMARY KEY (id)
);

CREATE TABLE apl_param.tab_banco (
	id 						    int4 				            NOT NULL,
	compe 				        int4 				            NOT NULL,
	ispb 				        int4 				            NOT NULL,
	nome 					    varchar(70) 		            NOT NULL,
	apelido 				    varchar(70) 		            NOT NULL,
	prioridade 				    int4 				            NOT NULL,
	CONSTRAINT pk_params_banco                                  PRIMARY KEY (id)
);

CREATE TABLE apl_param.tab_cep (
	cep                         varchar(10)                     NOT NULL,
	logradouro                  varchar(80)                         NULL,
	complemento                 varchar(60)                         NULL,
	bairro                      varchar(80)                         NULL,
	localidade                  varchar(80)                         NULL,
	estado                      varchar(60)                         NULL,
	uf                          char(2)                             NULL,
	ibge                        int4                                NULL,
	is_valido                   boolean                         NOT NULL,
	CONSTRAINT pk_params_cep                                    PRIMARY KEY (cep)
);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- MÓDULO : apl_acesso						DESCRIÇÃO: Módulo contendo tabelas de acesso e autenticação
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE SCHEMA apl_acesso;

CREATE TABLE apl_acesso.tab_usuario (
	id                          serial4                         NOT NULL,
	documento                   varchar(20)                     NOT NULL,
	email                       varchar(80)                     NOT NULL,
	is_expirado                 bool                            NOT NULL,
	is_bloqueado                bool                            NOT NULL,
	is_consultor                bool                            NOT NULL,
	login                       varchar(80)                     NOT NULL,
	nome                        varchar(50)                     NOT NULL,
	sobrenome                   varchar(50)                     NOT NULL,
	senha                       varchar(255)                    NOT NULL,
	cadastro_id                 int4                                NULL,
	CONSTRAINT pk_acessos_usuario                               PRIMARY KEY (id)
);

CREATE TABLE apl_acesso.tab_organizacao (
	id                          serial4                         NOT NULL,
	cpf_cnpj                    varchar(20)                     NOT NULL,
	nome                        varchar(100)                    NOT NULL,
	email                       varchar(80)                     NOT NULL,
	CONSTRAINT pk_acessos_organizacao                           PRIMARY KEY (id)
);

CREATE TABLE apl_acesso.tab_empresa (
	id                          serial4                         NOT NULL,
	cpf_cnpj                    varchar(20)                     NOT NULL,
	nome_fantasia               varchar(80)                     NOT NULL,
    sobrenome_social            varchar(80)                     NOT NULL,
	email                       varchar(80)                     NOT NULL,
	dt_aniversario              date                                NULL,
	rg_ie                       varchar(25)                         NULL,
	end_cep                     varchar(8)                          NULL,
	end_logradouro              varchar(100)                        NULL,
    end_numero                  varchar(8)                          NULL,
	end_complemento             varchar(40)                         NULL,
	end_referencia              varchar(100)                        NULL,
    end_telefone                int8                                NULL,
	end_bairro                  varchar(80)                         NULL,
	end_estado                  varchar(70)                         NULL,
	end_cidade                  varchar(100)                        NULL,
	end_uf                      char(2)                             NULL,
	end_ibge                    int4                                NULL,
	tel_celular                 int8                                NULL,
	tel_celular_whatsapp        bool                                NULL,
	tel_fixo                    int8                                NULL,
	organizacao_id              int4                            NOT NULL,
	ativ_com_prof               varchar(100)                        NULL,
	is_incompleto               bool                            NOT NULL,
	asaas_is_conta_empresa      bool                            NOT NULL,
	asaas_token                 varchar(250)                        NULL,
	asaas_webhook_token         varchar(30)                         NULL,
	asaas_tx_emissao_boleto     numeric(5,2)                        NULL,
	asaas_tx_emissao_pix        numeric(5,2)                        NULL,
	CONSTRAINT pk_acessos_empresa                               PRIMARY KEY (id)
);

ALTER TABLE apl_acesso.tab_empresa                             ADD CONSTRAINT fk_acessos_empresa_organizacao FOREIGN KEY (organizacao_id) REFERENCES apl_acesso.tab_organizacao(id);

CREATE TABLE apl_acesso.tab_usuario_empresa (
	usuario_id                  int4                            NOT NULL,
	empresa_id                  int4                            NOT NULL,
	is_padrao                   bool            DEFAULT 'false' NOT NULL,
	CONSTRAINT pk_acessos_usuario_empresa                       PRIMARY KEY (usuario_id,empresa_id),
    CONSTRAINT fk_acessos_usuario_empresa_emp                   FOREIGN KEY (empresa_id)            REFERENCES apl_acesso.tab_empresa(id),
    CONSTRAINT fk_acessos_usuario_empresa_user                  FOREIGN KEY (usuario_id)            REFERENCES apl_acesso.tab_usuario(id)
);

CREATE TABLE apl_acesso.tab_conta (
	id                          serial4                         NOT NULL,
	agencia                     varchar(10)                     NOT NULL,
	numero                      varchar(10)                     NOT NULL,
	saldo                       numeric(11,4)                   NOT NULL,
	legenda                     varchar(35)                     NOT NULL,
	descricao                   varchar(150)                        NULL,
	chave_pix                   varchar(100)                        NULL,
	banco_id                    int4                                NULL,
	empresa_id                  int4                            NOT NULL,
	is_conta_credito            bool                            NOT NULL,
	dia_vencimento              int4                                NULL,
	dias_intervalo              int4                                NULL,
	CONSTRAINT pk_acessos_conta                             PRIMARY KEY (id),
	CONSTRAINT fk_acessos_conta_empresa                     FOREIGN KEY (empresa_id)            REFERENCES apl_acesso.tab_empresa(id),
	CONSTRAINT fk_acessos_conta_banco                       FOREIGN KEY (banco_id)              REFERENCES apl_param.tab_banco(id)
);

CREATE TABLE apl_acesso.tab_aplicacao (
	id                          serial4                         NOT NULL,
	nome                        varchar(50)                     NOT NULL,
	localiza                    varchar(50)                     NOT NULL,
	tipo                        char(1)                             NULL,
	is_area                     bool                            NOT NULL,
	is_natureza                 bool                            NOT NULL,
	is_principal                bool                            NOT NULL,
	organizacao_id              int4                                NULL,
	CONSTRAINT pk_acessos_aplicacao                             PRIMARY KEY (id),
	CONSTRAINT fk_acessos_aplicacao_organizacao                 FOREIGN KEY (organizacao_id)        REFERENCES apl_acesso.tab_organizacao(id),
	CONSTRAINT ck_financeiro_aplicacao_tipo                    CHECK (tipo                         in ('R','D'))
);

CREATE TABLE apl_acesso.tab_forma_pagamento (
	id                          serial4                         NOT NULL,
	empresa_id                  int4                            NOT NULL,
	meio_pagto                  char(1)                         NOT NULL,
	nr_parcelas                 int4                            NOT NULL,
	taxa                        numeric(4,2)                    NOT NULL,
	conta_id                    int4                            NOT NULL,
	UNIQUE                      (empresa_id, meio_pagto,nr_parcelas),
	CONSTRAINT pk_acessos_empresa_forma_pagto                   PRIMARY KEY (id),
	CONSTRAINT fk_acessos_empresa_forma_pagto_empresa           FOREIGN KEY (empresa_id)            REFERENCES apl_acesso.tab_empresa(id),
	CONSTRAINT fk_acessos_empresa_forma_pagto_emp_conta         FOREIGN KEY (conta_id)              REFERENCES apl_acesso.tab_conta(id),
	CONSTRAINT ck_acessos_empresa_forma_pagto_tipo              CHECK (meio_pagto                   in ('A','B','C','D','W','X','Y','Z'))
);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- MÓDULO : apl_cadastro						DESCRIÇÃO: Módulo contendo tabelas de cadastros de entidades, como clientes, fornecedores e produtos entre outros
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE SCHEMA apl_cadastro;

CREATE TABLE apl_cadastro.tab_cadastro (
	id                          serial4                         NOT NULL,
    cpf_cnpj                    varchar(15)                         NULL,
    nome_fantasia               varchar(80)                     NOT NULL,
    localiza                    varchar(80)                         NULL,
    sobrenome_social            varchar(80)                     NOT NULL,
    email                       varchar(80)                     NOT NULL,
    dt_aniversario              date                                NULL,
    rg_ie                       varchar(25)                         NULL,
    end_cep                     varchar(8)                          NULL,
    end_logradouro              varchar(100)                        NULL,
    end_numero                  varchar(8)                          NULL,
    end_complemento             varchar(40)                         NULL,
    end_referencia              varchar(100)                        NULL,
    end_telefone                int8                                NULL,
    end_bairro                  varchar(80)                         NULL,
    end_estado                  varchar(70)                         NULL,
    end_cidade                  varchar(100)                        NULL,
    end_uf                      char(2)                             NULL,
    end_ibge                    int4                                NULL,
    tel_celular                 int8                                NULL,
    tel_celular_whatsapp        bool                                NULL,
    tel_fixo                    int8                                NULL,
    is_cliente                  bool                            NOT NULL,
    is_fornecedor               bool                            NOT NULL,
    ativ_com_prof               varchar(100)                        NULL,
    is_incompleto               bool                            NOT NULL,
    integra_asaas_id            varchar(50)                         NULL,
	organizacao_id              int4                            NOT NULL,
	CONSTRAINT tab_cadastro_cadastro                           PRIMARY KEY (id),
	CONSTRAINT fk_cadastros_cadastro_organizacao                FOREIGN KEY (organizacao_id)                       REFERENCES apl_acesso.tab_organizacao(id)
);

ALTER TABLE apl_acesso.tab_usuario                             ADD CONSTRAINT fk_acessos_usuario_cadastro FOREIGN KEY (cadastro_id) REFERENCES apl_cadastro.tab_cadastro(id);

CREATE TABLE apl_cadastro.tab_marca (
	id                          serial4                         NOT NULL,
    nome                        varchar(30)                     NOT NULL,
    localiza                    varchar(30)                     NOT NULL,
    nome_abreviado              varchar(20)                         NULL,
    sigla                       varchar(8)                          NULL,
    is_excluido                  bool                           NOT NULL,
    organizacao_id              int4                            NOT NULL,
	CONSTRAINT pk_cadastros_marca                               PRIMARY KEY (id),
	CONSTRAINT fk_cadastros_marca_organizacao                   FOREIGN KEY (organizacao_id)                       REFERENCES apl_acesso.tab_organizacao(id)
);
CREATE TABLE apl_cadastro.tab_modelo (
	id                          serial4                         NOT NULL,
	nome                        varchar(30)                     NOT NULL,
	localiza                    varchar(30)                     NOT NULL,
	nome_abreviado              varchar(20)                         NULL,
	sigla                       varchar(8)                          NULL,
	is_excluido                  bool                           NOT NULL,
	organizacao_id              int4                            NOT NULL,
	CONSTRAINT pk_cadastros_modelo                              PRIMARY KEY (id),
	CONSTRAINT fk_cadastros_modelo_organizacao                  FOREIGN KEY (organizacao_id)                       REFERENCES apl_acesso.tab_organizacao(id)
);
CREATE TABLE apl_cadastro.tab_categoria (
	id                          serial4                         NOT NULL,
    nome                        varchar(30)                     NOT NULL,
    localiza                    varchar(30)                     NOT NULL,
    nome_abreviado              varchar(20)                         NULL,
    sigla                       varchar(8)                          NULL,
    is_excluido                  bool                           NOT NULL,
    organizacao_id              int4                            NOT NULL,
	CONSTRAINT pk_cadastros_tab_categoria                       PRIMARY KEY (id),
	CONSTRAINT fk_cadastros_modelo_organizacao                  FOREIGN KEY (organizacao_id)                       REFERENCES apl_acesso.tab_organizacao(id)
);

CREATE TABLE apl_cadastro.tab_unid_medida (
	id                          serial4                         NOT NULL,
	nome                        varchar(30)                     NOT NULL,
	localiza                    varchar(30)                     NOT NULL,
	sigla                       varchar(8)                      NOT NULL,
    descricao                   varchar(80)                         NULL,
    conteudo                    numeric(8,4)                    NOT NULL,
	is_excluido                 bool                            NOT NULL,
	is_embalagem                bool                            NOT NULL,
	organizacao_id              int4                            NOT NULL,
	CONSTRAINT pk_cadastros_unid_medida                          PRIMARY KEY (id),
	CONSTRAINT fk_cadastros_unid_medida_organizacao              FOREIGN KEY (organizacao_id)                       REFERENCES apl_acesso.tab_organizacao(id)
);


CREATE TABLE apl_cadastro.tab_produto (
	id                          serial4                         NOT NULL,
	nome                        varchar(50)                     NOT NULL,
	localiza                    varchar(50)                     NOT NULL,
	descricao                   varchar(150)                        NULL,
	nome_abreviado              varchar(25)                         NULL,
	cod_barras                  varchar(15)                         NULL,
	sku                         varchar(15)                         NULL,
	vl_produto                  numeric(10,4)                   NOT NULL,
	saldo                       numeric(10,4)                   NOT NULL,
	tx_liquidacao               numeric(4,2)                    NOT NULL,
	is_excluido                 bool                            NOT NULL,
	is_servico                  bool                            NOT NULL,
	is_principal                bool                            NOT NULL,
	is_atualiza_saldo           bool                            NOT NULL,
	is_interno                  bool                            NOT NULL,
	app_is_visivel              bool                            NOT NULL,
	app_ordem_visualizacao      int4                            NOT NULL,
	marca_id                    int4                                NULL,
    modelo_id                   int4                                NULL,
    categoria_id                int4                                NULL,
    unid_medida_id              int4                            NOT NULL,
    unid_medida_sigla           varchar(10)                     NOT NULL,
    unid_embalagem_id           int4                                NULL,
    organizacao_id              int4                            NOT NULL,

	CONSTRAINT pk_cadastros_produto                             PRIMARY KEY (id),
	CONSTRAINT fk_cadastros_produto_organizacao                 FOREIGN KEY (organizacao_id)                       REFERENCES apl_acesso.tab_organizacao(id),
	CONSTRAINT fk_cadastros_produto_marca                       FOREIGN KEY (marca_id)                             REFERENCES apl_cadastro.tab_marca(id),
    CONSTRAINT fk_cadastros_produto_modelo                      FOREIGN KEY (modelo_id)                            REFERENCES apl_cadastro.tab_modelo(id),
    CONSTRAINT fk_cadastros_produto_categoria                   FOREIGN KEY (categoria_id)                         REFERENCES apl_cadastro.tab_categoria(id),
    CONSTRAINT fk_cadastros_produto_unid_medida_id              FOREIGN KEY (unid_medida_id)                       REFERENCES apl_cadastro.tab_unid_medida(id),
    CONSTRAINT fk_cadastros_produto_unid_embalagem_id           FOREIGN KEY (unid_embalagem_id)                    REFERENCES apl_cadastro.tab_unid_medida(id)

);


-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- MÓDULO : apl_contrato						DESCRIÇÃO: Módulo contendo tabelas com detalhes e informações sobre contratos e suas transações
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE SCHEMA apl_contrato;

CREATE TABLE apl_contrato.tab_contrato (
	id                          serial4                         NOT NULL,
	nr_contrato                 varchar(30)                     NOT NULL,
	descricao                   varchar(100)                    NOT NULL,
	sit_contrato                char(1)                         NOT NULL,
    tipo                        char(1)                         NOT NULL,
    cpt_data                    timestamp                       NOT NULL,
	cpt_dia                     date                            NOT NULL,
	cpt_ano                     int4                            NOT NULL,
	cpt_mes                     int4                            NOT NULL,
	cpt_periodo                 int4                            NOT NULL,
    cpt_competencia             int4                            NOT NULL,
    vl_previsto                 numeric(13,4)                   NOT NULL,
    vl_aplicado                 numeric(13,4)                   NOT NULL,
    vl_acres_itens              numeric(8,4)                    NOT NULL,
    vl_acres_pagto              numeric(8,4)                    NOT NULL,
    vl_desc_itens               numeric(8,4)                    NOT NULL,
    vl_desc_manual              numeric(8,2)                    NOT NULL,
	part_cadastro_id            int4                            NOT NULL,
	part_empresa_id             int4                            NOT NULL,
	part_organizacao_id         int4                            NOT NULL,
	part_usuario_id             int4                            NOT NULL,
	user_intermediador_id       int4                            NOT NULL,
	vigencia_inicio             timestamp                           NULL,
	vigencia_fim                timestamp                           NULL,
	CONSTRAINT pk_contratos_contrato                            PRIMARY KEY (id),
    CONSTRAINT fk_contratos_contrato_organizacao                FOREIGN KEY (part_organizacao_id)               REFERENCES apl_acesso.tab_organizacao(id),
    CONSTRAINT fk_contratos_contrato_empresa                    FOREIGN KEY (part_empresa_id)                   REFERENCES apl_acesso.tab_empresa(id),
    CONSTRAINT fk_contratos_contrato_usuario                    FOREIGN KEY (part_usuario_id)                   REFERENCES apl_acesso.tab_usuario(id),
    CONSTRAINT fk_contratos_contrato_intermediador              FOREIGN KEY (user_intermediador_id)             REFERENCES apl_acesso.tab_usuario(id),
    CONSTRAINT fk_contratos_contrato_cadastro                   FOREIGN KEY (part_cadastro_id)                  REFERENCES apl_cadastro.tab_cadastro(id),
    CONSTRAINT ck_contratos_contrato_situacao                   CHECK (sit_contrato                             in ('S','E','A','C','B','U','R','P','V','N')),
    CONSTRAINT ck_contratos_contrato_tipo                       CHECK (tipo                                     in ('C','V','L','S')),
    UNIQUE                                                      (nr_contrato)

);
CREATE TABLE apl_contrato.tab_contrato_item (
	id                          serial4                         NOT NULL,
	descricao                   varchar(250)                    NOT NULL,
	quant                       numeric(13,4)                   NOT NULL,
    vl_unit                     numeric(13,4)                   NOT NULL,
    vl_previsto                 numeric(13,4)                   NOT NULL,
    vl_aplicado                 numeric(13,4)                   NOT NULL,
    vl_variacao                 numeric(13,4)                   NOT NULL,
    prod_id                     int4                            NOT NULL,
	prod_cod_barras             varchar(20)                     NOT NULL,
	prod_und_med_sigla          varchar(8)                      NOT NULL,
	prod_preco                  numeric(13,4)                   NOT NULL,
	prod_tx_liquidacao          numeric(5,2)                    NOT NULL,
	prod_saldo                  numeric(13,4)                   NOT NULL,
	contrato_id                 int4                                NULL,
	CONSTRAINT pk_contratos_contrato_item                       PRIMARY KEY (id),
    CONSTRAINT fk_contratos_contrato_item_contrato              FOREIGN KEY (contrato_id)                       REFERENCES apl_contrato.tab_contrato(id),
    CONSTRAINT fk_contratos_contrato_item_produto               FOREIGN KEY (prod_id)                           REFERENCES apl_cadastro.tab_produto(id)
);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- MÓDULO : apl_financeiro						DESCRIÇÃO: Módulo contendo tabelas para registrar lançamentos da transações, pagamentos e parcelamentos
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE SCHEMA apl_financeiro;


CREATE TABLE apl_financeiro.tab_transacao (
	id                          serial4                         NOT NULL,
	tipo                        char(1)                         NOT NULL,
	nr_documento                varchar(25)                     NOT NULL,
	titulo                      varchar(40)                     NOT NULL,
    descricao                   varchar(250)                    NOT NULL,
	cpt_data                    timestamp                       NOT NULL,
    cpt_dia                     date                            NOT NULL,
	cpt_ano                     int4                            NOT NULL,
	cpt_mes                     int4                            NOT NULL,
	cpt_periodo                 int4                            NOT NULL,
	cpt_competencia             int4                            NOT NULL,
	part_cadastro_id            int4                            NOT NULL,
	part_empresa_id             int4                            NOT NULL,
	part_organizacao_id         int4                            NOT NULL,
	part_usuario_id             int4                            NOT NULL,
	vl_informado                numeric(13,4)                   NOT NULL,
	vl_operacional              numeric(13,4)                   NOT NULL,
	observacao                  varchar(100)                        NULL,
    aplic_area                  int4                            NOT NULL,
    aplic_natureza              int4                           	NOT NULL,
    nr_contrato                 varchar(30)                          NULL,
    CONSTRAINT pk_finaceiro_transacao                          PRIMARY KEY (id),
	CONSTRAINT fk_financeiro_transacao_organizacao             FOREIGN KEY (part_organizacao_id)        REFERENCES apl_acesso.tab_organizacao(id),
    CONSTRAINT fk_financeiro_transacao_empresa                 FOREIGN KEY (part_empresa_id)            REFERENCES apl_acesso.tab_empresa(id),
    CONSTRAINT fk_financeiro_transacao_usuario                 FOREIGN KEY (part_usuario_id)            REFERENCES apl_acesso.tab_usuario(id),
    CONSTRAINT fk_financeiro_transacao_cadastro                FOREIGN KEY (part_cadastro_id)           REFERENCES apl_cadastro.tab_cadastro(id),
    CONSTRAINT fk_financeiro_transacao_area                    FOREIGN KEY (aplic_area)                 REFERENCES apl_acesso.tab_aplicacao(id),
    CONSTRAINT fk_financeiro_transacao_natureza                FOREIGN KEY (aplic_natureza)             REFERENCES apl_acesso.tab_aplicacao(id),
    CONSTRAINT ck_financeiro_transacao_tipo                    CHECK (tipo                              in ('R','D'))
);
CREATE TABLE apl_financeiro.tab_transacao_rateio (
	id                          serial4                         NOT NULL,
	vl_original                 numeric(13,4)                   NOT NULL,
	meio_pagto                  char(1)                         NOT NULL,
	tx_meio_pagto               numeric(4,2)                    NOT NULL,
	vl_pago                     numeric(13,4)                   NOT NULL,
	vl_parcela                  numeric(13,4)                       NULL,
	num_parcelas                int4                                NULL,
    dt_pri_vencto               date                                NULL,
	transacao_id                int4                                NULL,
	CONSTRAINT pk_financeiro_transacao_rateio                   PRIMARY KEY (id),
	CONSTRAINT fk_financeiro_transacao_rateio_transacao         FOREIGN KEY (transacao_id)               REFERENCES apl_financeiro.tab_transacao(id),
	CONSTRAINT ck_financeiro_transacao_rateio_meio_pagto        CHECK (meio_pagto                        in ('A','B','C','D','X','Z'))
);

CREATE TABLE apl_financeiro.tab_parcelamento (
	id                          serial4                         NOT NULL,
	transacao_id                int4                                NULL,
	descricao                   varchar(80)                     NOT NULL,
	dt_vencto                   date                            NOT NULL,
    num_parcela                 int4                            NOT NULL,
	vl_original                 numeric(9,2)                    NOT NULL,
    vl_multa                    numeric(7,2)                    NOT NULL,
    vl_juros                    numeric(7,2)                    NOT NULL,
    vl_correcao                 numeric(7,2)                    NOT NULL,
    vl_desconto                 numeric(7,2)                    NOT NULL,
    vl_amortizado               numeric(7,2)                    NOT NULL,
    vl_atual                    numeric(9,2)                    NOT NULL,
	meio_pagto                  char(1)                         NOT NULL,
	cadastro_id                 int4                            NOT NULL,
	CONSTRAINT pk_finaceiro_parcelamento                        PRIMARY KEY (id),
	CONSTRAINT fk_financeiro_parcelamento_transacao             FOREIGN KEY (transacao_id)               REFERENCES apl_financeiro.tab_transacao(id),
	CONSTRAINT fk_financeiro_parcelamento_cadastro              FOREIGN KEY (cadastro_id)                REFERENCES apl_cadastro.tab_cadastro(id),
	CONSTRAINT ck_financeiro_parcelamento_meio_pagamento        CHECK (meio_pagto                        in ('A','B','C','D','W','X','Y','Z'))
);

CREATE TABLE apl_financeiro.tab_parcelamento_parcela (
	id                          serial4                         NOT NULL,
	descricao                   varchar(80)                     NOT NULL,
	dt_vencto                   date                            NOT NULL,
    num_parcela                 int4                            NOT NULL,
    vl_original                 numeric(9,2)                    NOT NULL,
    vl_multa                    numeric(7,2)                    NOT NULL,
    vl_juros                    numeric(7,2)                    NOT NULL,
    vl_correcao                 numeric(7,2)                    NOT NULL,
    vl_desconto                 numeric(7,2)                    NOT NULL,
    vl_amortizado               numeric(7,2)                    NOT NULL,
    vl_atual                    numeric(9,2)                    NOT NULL,
	pend_is_atrasada             bool                            NOT NULL,
    pend_dias_atraso             int4                            NOT NULL,
	pend_is_negociada            bool                            NOT NULL,
    pend_dt_prox_vencto          date                                NULL,
	al_correcao                 numeric(5,2)                    NOT NULL,
    al_juros                    numeric(5,2)                    NOT NULL,
    al_multa                    numeric(5,2)                    NOT NULL,
	quit_is_efetuada            bool                            NOT NULL,
	quit_data                   date                                NULL,
	observacao                  varchar(150)                        NULL,
	parc_lancto_id              int4                                NULL,
	bol_is_solicitado           bool                            NOT NULL,
	bol_repasse_tipo            char(1)                             NULL,
	bol_is_repasse_confirmado   bool                            NOT NULL,
    bol_status                  char(1)                             NULL,
    bol_nr_autorizacao          varchar(40)                         NULL,
    bol_url_impressao           varchar(100)                        NULL,
    bol_linha_digitavel         varchar(60)                         NULL,
    bol_vl_original             numeric(10,2)                       NULL,
    bol_vl_tx_impressao         numeric(5,2)                        NULL,
    bol_vl_impresso             numeric(10,2)                       NULL,
    bol_vl_compensado           numeric(10,2)                       NULL,
    bol_tipo_compensacao        varchar(30)                         NULL,
    bol_dt_pagamento            date                                NULL,
    bol_dt_compensacao          date                                NULL,
	CONSTRAINT pk_finaceiro_lancto_parcela                    PRIMARY KEY (id),
	CONSTRAINT fk_financeiro_lancto_parc_lancto               FOREIGN KEY (parc_lancto_id)            REFERENCES apl_financeiro.tab_parcelamento(id)
);

CREATE TABLE apl_financeiro.tab_parcelamento_parcela_pagto (
	id                          serial4                         NOT NULL,
	cpt_competencia         int4                                NOT NULL,
	dt_pagto                    date                            NOT NULL,
	valor                       numeric(9,2)                    NOT NULL,
	meio_pagto                  char(1)                         NOT NULL,
	parcela_id                  int4                            NOT NULL,
	parcelamento_id             int4                            NOT NULL,
	usuario_id                  int4                            NOT NULL,
	conta_banco_id              int4                            NOT NULL,
	bol_nr_autorizacao          varchar(40)                         NULL,
	bol_vl_original             numeric(9,2)                        NULL,

	CONSTRAINT pk_finaceiro_lancto_parcela_pagto              PRIMARY KEY (id),
	CONSTRAINT fk_financeiro_lancto_parcela_pagto_parcel       FOREIGN KEY (parcela_id)            REFERENCES apl_financeiro.tab_parcelamento_parcela(id),
	CONSTRAINT fk_financeiro_lancto_parcela_pagto_parcelto     FOREIGN KEY (parcelamento_id)       REFERENCES apl_financeiro.tab_parcelamento(id),
	CONSTRAINT fk_financeiro_lancto_parcela_pagto_conta_banco  FOREIGN KEY (conta_banco_id)        REFERENCES apl_acesso.tab_conta(id),
	CONSTRAINT fk_financeiro_lancto_parcela_pagto_usuario      FOREIGN KEY (usuario_id)            REFERENCES apl_acesso.tab_usuario(id),
	CONSTRAINT ck_financeiro_parclto_parc_meio_pagamento_tipo  CHECK (meio_pagto                   in ('A','B','C','D','X','Y','Z'))
);

CREATE TABLE apl_financeiro.tab_pagamento (
	id                          serial4                         NOT NULL,
	transacao_id                int4                                NULL,
	parcelamento_id             int4                                NULL,
	descricao                   varchar(80)                     NOT NULL,
	conta_id                    int4                            NOT NULL,
	meio_pagto                  char(1)                         NOT NULL,
	vl_informado                numeric(13,4)                   NOT NULL,
	vl_operacional              numeric(13,4)                   NOT NULL,
	cadastro_id                 int4                            NOT NULL,

	CONSTRAINT pk_finaceiro_pagamento                          PRIMARY KEY (id),
	CONSTRAINT fk_financeiro_pagamento_transacao               FOREIGN KEY (transacao_id)               REFERENCES apl_financeiro.tab_transacao(id),
	CONSTRAINT fk_financeiro_pagamento_parcelamento            FOREIGN KEY (parcelamento_id)            REFERENCES apl_financeiro.tab_parcelamento(id),
	CONSTRAINT fk_financeiro_pagamento_conta_banco             FOREIGN KEY (conta_id)                   REFERENCES apl_acesso.tab_conta(id),
    CONSTRAINT fk_financeiro_pagamento_cadastro                FOREIGN KEY (cadastro_id)                REFERENCES apl_cadastro.tab_cadastro(id),
    CONSTRAINT ck_financeiro_pagamento_meio_pagamento          CHECK (meio_pagto                        in ('A','B','C','D','W','X','Y','Z'))
);
