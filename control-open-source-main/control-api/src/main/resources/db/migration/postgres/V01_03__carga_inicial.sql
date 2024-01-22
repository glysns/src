-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- CARGA INICIAL
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--INICIO DO CREDENCIAMENTO
INSERT INTO apl_acesso.tab_organizacao (cpf_cnpj, nome, email) VALUES('00000000000', 'ORGANIZACAO SISTEMA', 'sistema@digytal.com.br');
INSERT INTO apl_acesso.tab_empresa (cpf_cnpj, nome_fantasia, sobrenome_social, email, dt_aniversario, rg_ie, end_cep, end_logradouro, end_numero, end_complemento, end_referencia, end_telefone, end_bairro, end_estado, end_cidade, end_uf, end_ibge, tel_celular, tel_celular_whatsapp, tel_fixo, organizacao_id, ativ_com_prof, is_incompleto,asaas_is_conta_empresa) VALUES('00000000000', 'EMPRESA SISTEMA', 'EMPRESA SISTEMA', 'sistema@digytal.com.br', '2023-09-23', '123', '64000020', 'AVENIDA FREI SERAFIM', '123', 'LADO IMPAR', '', NULL, 'CENTRO', 'PIAUI', 'TERESINA', 'PI', 2211001, 99999999999, false, NULL, 1, '', false,false);
INSERT INTO apl_acesso.tab_usuario (documento, email, is_expirado, is_bloqueado, is_consultor, login, nome, sobrenome, senha, cadastro_id) VALUES('00000000000', 'sistema@digytal.com.br', false, false, false, '00000000000', 'USUARIO SISTEMA', 'USUARIO SISTEMA', '$2a$10$VdeqgdPlwOIwKJd7SzNZmOTADPvH3UH40GFU7A7r4RzEThGhEuW9C', NULL);
INSERT INTO apl_acesso.tab_usuario_empresa (usuario_id, empresa_id, is_padrao) VALUES(1, 1, false);
--UNIDADE DE MEDIDA
INSERT INTO apl_cadastro.tab_unid_medida (sigla, nome, localiza, descricao, conteudo, is_excluido, is_embalagem, organizacao_id) VALUES('UND', 'Unidade', 'UNIDADE', 'Unidade de medida padrão', 1.0000, false, false, 1);
--CONTAS
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 9999,'BANCO BALCAO','BANCO BALCAO',0,0,1);
INSERT INTO apl_acesso.tab_conta (agencia, numero, saldo, legenda, descricao, chave_pix, banco_id, empresa_id, is_conta_credito, dia_vencimento, dias_intervalo) VALUES('AG01-0', 'CC01-0', 0.00, 'Conta Carteira', 'Conta destinada as movimentações financeiras de meio de pagamento em DINHEIRO, conhecida como conta carteira', NULL, 9999, 1, false, NULL, NULL);
INSERT INTO apl_acesso.tab_conta (agencia, numero, saldo, legenda, descricao, chave_pix, banco_id, empresa_id, is_conta_credito, dia_vencimento, dias_intervalo) VALUES('AG02-0', 'CC02-0', 0.00, 'Conta Banco', 'Conta destinada as movimentações financeiras de meio de pagamento como PIX, DEBITO, CREDITO, BOLETO', NULL, 9999, 1, false, NULL, NULL);
--FORMAS DE PAGAMENTO
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'A', 1, 0.00, 1); -- AVISTA
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'B', 1, 1.00, 2); -- BOLETO
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'X', 1, 0.50, 2); -- PIX
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'D', 1, 1.00, 2); -- DEBITO
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'C', 1, 1.50, 2); -- CREDITO
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'C', 2, 2.00, 2);
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'C', 3, 3.00, 2);
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'C', 4, 4.00, 2);
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'Z', 1, 1.00, 2); -- PRAZO
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'Z', 2, 1.50, 2);
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'Z', 3, 1.50, 2);
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'Z', 4, 2.00, 2);
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'Z', 5, 2.00, 2);
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'Z', 6, 2.00, 2);
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'Z', 7, 2.50, 2);
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'Z', 8, 2.50, 2);
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'Z', 9, 2.50, 2);
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'Z', 10, 3.50, 2);
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'Z', 11, 3.50, 2);
INSERT INTO apl_acesso.tab_forma_pagamento (empresa_id, meio_pagto, nr_parcelas, taxa, conta_id) VALUES(1, 'Z', 12, 4.00, 2);

--APLICAÇÕES
INSERT INTO apl_acesso.tab_aplicacao (nome, tipo, is_area, is_natureza, is_principal, organizacao_id, localiza) VALUES('Receitas', 'R', false, true, true, 1,'RECEITAS');
INSERT INTO apl_acesso.tab_aplicacao (nome, tipo, is_area, is_natureza, is_principal, organizacao_id, localiza) VALUES('Despesas', 'D', false, true, true, 1,'DESPESAS');
--- FIM CREDENCIAMENTO
INSERT INTO apl_acesso.tab_aplicacao (nome, is_area, is_natureza, is_principal, localiza) VALUES('Indefinido(a)', true, false,false,'INDEFINIDO');
INSERT INTO apl_cadastro.tab_cadastro (cpf_cnpj, nome_fantasia, sobrenome_social, dt_aniversario, rg_ie, email, end_cep, end_logradouro, end_numero, end_complemento, end_referencia, end_telefone, end_bairro, end_estado, end_cidade, end_uf, end_ibge, tel_celular, tel_celular_whatsapp, tel_fixo, is_cliente, is_fornecedor, ativ_com_prof, organizacao_id, is_incompleto) VALUES('00000000000', '**CADASTRO PADRAO**', 'CADASTRO PADRAO', '1999-06-30', '', 'sem@email.com.br', '64965970', '', '', '', '', NULL, '', '', '', '', 2201101, 98908908908, false, NULL, true, false, '', 1, false);
INSERT INTO apl_cadastro.tab_unid_medida (nome, localiza, sigla, descricao, conteudo, is_excluido, is_embalagem, organizacao_id) VALUES('Unidade', 'UNIDADE', 'UND', 'Unidade de medida padrão', 1.0000, false, false, 1);
INSERT INTO apl_cadastro.tab_produto (nome, localiza, descricao, nome_abreviado, cod_barras, sku, vl_produto, saldo, tx_liquidacao, is_excluido, is_servico, is_principal, is_atualiza_saldo, is_interno, app_is_visivel, app_ordem_visualizacao, marca_id, modelo_id, categoria_id, unid_medida_id, unid_medida_sigla, unid_embalagem_id, organizacao_id) VALUES('PRODUTO PADRAO', 'PRODUTO PADRAO', 'PRODUTO PADRAO', 'PRODUTO PADRAO', '', '', 0.0000, 0.0000, 0.00, false, false, false, false, false, false, 0, NULL, NULL, NULL, 1, 'UND', NULL, 1);

INSERT INTO apl_param.tab_cep (cep, logradouro, complemento, bairro, localidade, estado, uf, ibge, is_valido) VALUES('99999999', 'INDEFINIDO', '', 'INDEFINIDO', 'BRASIL', 'BRASIL', 'BR', 9999999, true);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 1,'BANCO DO BRASIL S.A','BANCO DO BRASIL S.A',0,1,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 237,'BRADESCO S.A','BRADESCO S.A',60746948,237,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 335,'BANCO DIGIO S.A','BANCO DIGIO S.A',27098060,335,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 260,'NU PAGAMENTOS S.A','NU PAGAMENTOS S.A',18236120,260,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 290,'PAGSEGURO INTERNET S.A','PAGSEGURO INTERNET S.A',8561701,290,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 380,'PICPAY SERVICOS S.A.','PICPAY SERVICOS S.A.',22896431,380,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 323,'MERCADO PAGO','MERCADO PAGO',10573521,323,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 2371,'NEXT BANK','NEXT BANK',60746948,237,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 637,'BANCO SOFISA S.A','BANCO SOFISA S.A',60889128,637,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 77,'BANCO INTER S.A','BANCO INTER S.A',416968,77,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 341,'ITAU UNIBANCO S.A','ITAU UNIBANCO S.A',60701190,341,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 104,'CAIXA ECONOMICA FEDERAL','CAIXA ECONOMICA FEDERAL',360305,104,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 33,'BANCO SANTANDER BRASIL S.A','BANCO SANTANDER BRASIL S.A',90400888,33,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 212,'BANCO ORIGINAL S.A','BANCO ORIGINAL S.A',92894922,212,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 756,'BANCOOB','BANCOOB',2038232,756,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 413,'BANCO VOTORANTIM S.A','BANCO VOTORANTIM S.A',59588111,413,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 655,'NEON PAGAMENTOS S.A','NEON PAGAMENTOS S.A',59588111,655,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 41,'BANRISUL  BANCO DO ESTADO DO RIO GRANDE DO SUL S.A','BANRISUL  BANCO DO ESTADO DO RIO GRANDE DO SUL S.A',92702067,41,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 389,'BANCO MERCANTIL DO BRASIL S.A','BANCO MERCANTIL DO BRASIL S.A',17184037,389,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 422,'BANCO SAFRA S.A','BANCO SAFRA S.A',58160789,422,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 70,'BANCO DE BRASILIA','BANCO DE BRASILIA',208,70,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 136,'UNICRED COOPERATIVA','UNICRED COOPERATIVA',315557,136,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 741,'BANCO RIBEIRAO PRETO','BANCO RIBEIRAO PRETO',517645,741,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 739,'BANCO CETELEM S.A','BANCO CETELEM S.A',558456,739,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 743,'BANCO SEMEAR S.A','BANCO SEMEAR S.A',795423,743,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 100,'PLANNER CORRETORA DE VALORES S.A','PLANNER CORRETORA DE VALORES S.A',806535,100,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 96,'BANCO B3 S.A','BANCO B3 S.A',997185,96,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 747,'Banco RABOBANK INTERNACIONAL DO BRASIL S.A','Banco RABOBANK INTERNACIONAL DO BRASIL S.A',1023570,747,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 748,'SICREDI S.A','SICREDI S.A',1181521,748,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 752,'BNP PARIBAS BRASIL S.A','BNP PARIBAS BRASIL S.A',1522368,752,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 91,'UNICRED CENTRAL RS','UNICRED CENTRAL RS',1634601,91,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 399,'KIRTON BANK','KIRTON BANK',1701201,399,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 108,'PORTOCRED S.A','PORTOCRED S.A',1800019,108,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 757,'BANCO KEB HANA DO BRASIL S.A','BANCO KEB HANA DO BRASIL S.A',2318507,757,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 102,'XP INVESTIMENTOS S.A','XP INVESTIMENTOS S.A',2332886,102,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 84,'UNIPRIME NORTE DO PARANA','UNIPRIME NORTE DO PARANA',2398976,84,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 180,'CM CAPITAL MARKETS CCTVM LTDA','CM CAPITAL MARKETS CCTVM LTDA',2685483,180,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 66,'BANCO MORGAN STANLEY S.A','BANCO MORGAN STANLEY S.A',2801938,66,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 15,'UBS BRASIL CCTVM S.A','UBS BRASIL CCTVM S.A',2819125,15,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 143,'TREVISO CC S.A','TREVISO CC S.A',2992317,143,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 62,'HIPERCARD BM S.A','HIPERCARD BM S.A',3012230,62,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 74,'BCO. J.SAFRA S.A','BCO. J.SAFRA S.A',3017677,74,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 99,'UNIPRIME CENTRAL CCC LTDA','UNIPRIME CENTRAL CCC LTDA',3046391,99,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 25,'BANCO ALFA S.A.','BANCO ALFA S.A.',3323840,25,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 75,'BCO ABN AMRO S.A','BCO ABN AMRO S.A',3532415,75,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 40,'BANCO CARGILL S.A','BANCO CARGILL S.A',3609817,40,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 190,'SERVICOOP','SERVICOOP',3973814,190,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 63,'BANCO BRADESCARD','BANCO BRADESCARD',4184779,63,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 191,'NOVA FUTURA CTVM LTDA','NOVA FUTURA CTVM LTDA',4257795,191,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 64,'GOLDMAN SACHS DO BRASIL BM S.A','GOLDMAN SACHS DO BRASIL BM S.A',4332281,64,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 97,'CCC NOROESTE BRASILEIRO LTDA','CCC NOROESTE BRASILEIRO LTDA',4632856,97,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 16,'CCM DESP TRANS SC E RS','CCM DESP TRANS SC E RS',4715685,16,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 12,'BANCO INBURSA','BANCO INBURSA',4866275,12,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 3,'BANCO DA AMAZONIA S.A','BANCO DA AMAZONIA S.A',4902979,3,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 60,'CONFIDENCE CC S.A','CONFIDENCE CC S.A',4913129,60,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 37,'BANCO DO ESTADO DO PARA S.A','BANCO DO ESTADO DO PARA S.A',4913711,37,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 159,'CASA CREDITO S.A','CASA CREDITO S.A',5442029,159,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 172,'ALBATROSS CCV S.A','ALBATROSS CCV S.A',5452073,172,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 85,'COOP CENTRAL AILOS','COOP CENTRAL AILOS',5463212,85,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 114,'CENTRAL COOPERATIVA DE CREDITO NO','CENTRAL COOPERATIVA DE CREDITO NO',0,114,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 0,'ESTADO DO ESPIRITO SANTO','ESTADO DO ESPIRITO SANTO',5790149,0,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 36,'BANCO BBI S.A','BANCO BBI S.A',6271464,36,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 394,'BANCO BRADESCO FINANCIAMENTOS S.A','BANCO BRADESCO FINANCIAMENTOS S.A',7207996,394,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 4,'BANCO DO NORDESTE DO BRASIL S.A.','BANCO DO NORDESTE DO BRASIL S.A.',7237373,4,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 320,'BANCO CCB BRASIL S.A','BANCO CCB BRASIL S.A',7450604,320,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 189,'HS FINANCEIRA','HS FINANCEIRA',7512441,189,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 105,'LECCA CFI S.A','LECCA CFI S.A',7652226,105,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 76,'BANCO KDB BRASIL S.A.','BANCO KDB BRASIL S.A.',7656500,76,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 82,'BANCO TOPAZIO S.A','BANCO TOPAZIO S.A',7679404,82,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 286,'CCR DE OURO','CCR DE OURO',7853842,286,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 93,'POLOCRED SCMEPP LTDA','POLOCRED SCMEPP LTDA',7945233,93,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 273,'CCR DE SAO MIGUEL DO OESTE','CCR DE SAO MIGUEL DO OESTE',8253539,273,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 157,'ICAP DO BRASIL CTVM LTDA','ICAP DO BRASIL CTVM LTDA',9105360,157,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 183,'SOCRED S.A','SOCRED S.A',9210106,183,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 14,'NATIXIS BRASIL S.A','NATIXIS BRASIL S.A',9274232,14,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 130,'CARUANA SCFI','CARUANA SCFI',9313766,130,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 127,'CODEPE CVC S.A','CODEPE CVC S.A',9512542,127,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 79,'BANCO ORIGINAL DO AGRONEGOCIO S.A','BANCO ORIGINAL DO AGRONEGOCIO S.A',9516419,79,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 81,'BBN BANCO BRASILEIRO DE NEGOCIOS S.A','BBN BANCO BRASILEIRO DE NEGOCIOS S.A',10264663,81,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 118,'STANDARD CHARTERED BI S.A','STANDARD CHARTERED BI S.A',11932017,118,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 133,'CRESOL CONFEDERACAO','CRESOL CONFEDERACAO',10398952,133,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 121,'BANCO AGIBANK S.A','BANCO AGIBANK S.A',10664513,121,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 83,'BANCO DA CHINA BRASIL S.A','BANCO DA CHINA BRASIL S.A',10690848,83,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 138,'GET MONEY CC LTDA','GET MONEY CC LTDA',10853017,138,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 24,'BCO BANDEPE S.A','BCO BANDEPE S.A',10866788,24,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 95,'BANCO CONFIDENCE DE CAMBIO S.A','BANCO CONFIDENCE DE CAMBIO S.A',11703662,95,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 94,'BANCO FINAXIS','BANCO FINAXIS',11758741,94,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 276,'SENFF S.A','SENFF S.A',11970623,276,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 137,'MULTIMONEY CC LTDA','MULTIMONEY CC LTDA',12586596,137,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 92,'BRK S.A','BRK S.A',12865507,92,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 47,'BANCO BCO DO ESTADO DE SERGIPE S.A','BANCO BCO DO ESTADO DE SERGIPE S.A',13009717,47,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 144,'BEXS BANCO DE CAMBIO S.A.','BEXS BANCO DE CAMBIO S.A.',13059145,144,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 126,'BR PARTNERS BI','BR PARTNERS BI',13220493,126,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 301,'BPP INSTITUICAO DE PAGAMENTOS S.A','BPP INSTITUICAO DE PAGAMENTOS S.A',13370835,301,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 173,'BRL TRUST DTVM SA','BRL TRUST DTVM SA',13486793,173,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 119,'BANCO WESTERN UNION','BANCO WESTERN UNION',13720915,119,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 254,'PARANA BANCO S.A','PARANA BANCO S.A',14388334,254,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 268,'BARIGUI CH','BARIGUI CH',14511781,268,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 107,'BANCO BOCOM BBM S.A','BANCO BOCOM BBM S.A',15114366,107,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 412,'BANCO CAPITAL S.A','BANCO CAPITAL S.A',15173776,412,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 124,'BANCO WOORI BANK DO BRASIL S.A','BANCO WOORI BANK DO BRASIL S.A',15357060,124,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 149,'FACTA S.A. CFI','FACTA S.A. CFI',15581638,149,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 197,'STONE PAGAMENTOS S.A','STONE PAGAMENTOS S.A',16501555,197,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 142,'BROKER BRASIL CC LTDA','BROKER BRASIL CC LTDA',16944141,142,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 184,'BANCO ITAU BBA S.A','BANCO ITAU BBA S.A',17298092,184,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 634,'BANCO TRIANGULO S.A','BANCO TRIANGULO S.A',17351180,634,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 545,'SENSO CCVM S.A','SENSO CCVM S.A',17352220,545,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 132,'ICBC DO BRASIL BM S.A','ICBC DO BRASIL BM S.A',17453575,132,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 298,'VIPS CC LTDA','VIPS CC LTDA',17772370,298,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 129,'UBS BRASIL BI S.A','UBS BRASIL BI S.A',18520834,129,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 128,'MS BANK S.A BANCO DE CAMBIO','MS BANK S.A BANCO DE CAMBIO',19307785,128,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 194,'PARMETAL DTVM LTDA','PARMETAL DTVM LTDA',20155248,194,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 310,'VORTX DTVM LTDA','VORTX DTVM LTDA',22610500,310,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 163,'COMMERZBANK BRASIL S.A BANCO MULTIPLO','COMMERZBANK BRASIL S.A BANCO MULTIPLO',23522214,163,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 280,'AVISTA S.A','AVISTA S.A',23862762,280,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 146,'GUITTA CC LTDA','GUITTA CC LTDA',24074692,146,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 279,'CCR DE PRIMAVERA DO LESTE','CCR DE PRIMAVERA DO LESTE',26563270,279,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 182,'DACASA FINANCEIRA S/A','DACASA FINANCEIRA S/A',27406222,182,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 278,'GENIAL INVESTIMENTOS CVM S.A','GENIAL INVESTIMENTOS CVM S.A',27652684,278,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 271,'IB CCTVM LTDA','IB CCTVM LTDA',27842177,271,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 21,'BANCO BANESTES S.A','BANCO BANESTES S.A',28127603,21,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 246,'BANCO ABC BRASIL S.A','BANCO ABC BRASIL S.A',28195667,246,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 751,'SCOTIABANK BRASIL','SCOTIABANK BRASIL',29030467,751,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 208,'BANCO BTG PACTUAL S.A','BANCO BTG PACTUAL S.A',30306294,208,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 746,'BANCO MODAL S.A','BANCO MODAL S.A',30723886,746,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 241,'BANCO CLASSICO S.A','BANCO CLASSICO S.A',31597552,241,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 612,'BANCO GUANABARA S.A','BANCO GUANABARA S.A',31880826,612,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 604,'BANCO INDUSTRIAL DO BRASIL S.A','BANCO INDUSTRIAL DO BRASIL S.A',31895683,604,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 505,'BANCO CREDIT SUISSE','BANCO CREDIT SUISSE',32062580,505,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 196,'BANCO FAIR CC S.A','BANCO FAIR CC S.A',32648370,196,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 300,'BANCO LA NACION ARGENTINA','BANCO LA NACION ARGENTINA',33042151,300,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 477,'CITIBANK N.A','CITIBANK N.A',33042953,477,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 266,'BANCO CEDULA S.A','BANCO CEDULA S.A',33132044,266,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 122,'BANCO BRADESCO BERJ S.A','BANCO BRADESCO BERJ S.A',33147315,122,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 376,'BANCO J.P. MORGAN S.A','BANCO J.P. MORGAN S.A',33172537,376,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 473,'BANCO CAIXA GERAL BRASIL S.A','BANCO CAIXA GERAL BRASIL S.A',33466988,473,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 745,'BANCO CITIBANK S.A','BANCO CITIBANK S.A',33479023,745,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 120,'BANCO RODOBENS S.A','BANCO RODOBENS S.A',33603457,120,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 265,'BANCO FATOR S.A','BANCO FATOR S.A',33644196,265,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 7,'BNDES','BNDES',33657248,7,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 188,'ATIVA S.A INVESTIMENTOS','ATIVA S.A INVESTIMENTOS',33775974,188,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 134,'BGC LIQUIDEZ DTVM LTDA','BGC LIQUIDEZ DTVM LTDA',33862244,134,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 641,'BANCO ALVORADA S.A','BANCO ALVORADA S.A',33870163,641,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 29,'BANCO ITAU CONSIGNADO S.A','BANCO ITAU CONSIGNADO S.A',33885724,29,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 243,'BANCO MAXIMA S.A','BANCO MAXIMA S.A',33923798,243,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 78,'HAITONG BI DO BRASIL S.A','HAITONG BI DO BRASIL S.A',34111187,78,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 111,'BANCO OLIVEIRA TRUST DTVM S.A','BANCO OLIVEIRA TRUST DTVM S.A',36113876,111,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 17,'BNY MELLON BANCO S.A','BNY MELLON BANCO S.A',42272526,17,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 174,'PERNAMBUCANAS FINANC S.A','PERNAMBUCANAS FINANC S.A',43180355,174,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 495,'LA PROVINCIA BUENOS AIRES BANCO','LA PROVINCIA BUENOS AIRES BANCO',44189447,495,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 125,'BRASIL PLURAL S.A BANCO','BRASIL PLURAL S.A BANCO',45246410,125,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 488,'JPMORGAN CHASE BANK','JPMORGAN CHASE BANK',46518205,488,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 65,'BANCO ANDBANK S.A','BANCO ANDBANK S.A',48795256,65,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 492,'ING BANK N.V','ING BANK N.V',49336860,492,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 250,'BANCO BCV','BANCO BCV',50585090,250,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 145,'LEVYCAM CCV LTDA','LEVYCAM CCV LTDA',50579044,145,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 494,'BANCO REP ORIENTAL URUGUAY','BANCO REP ORIENTAL URUGUAY',51938876,494,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 253,'BEXS CC S.A','BEXS CC S.A',52937216,253,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 269,'HSBC BANCO DE INVESTIMENTO','HSBC BANCO DE INVESTIMENTO',53518684,269,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 213,'BCO ARBI S.A','BCO ARBI S.A',54403563,213,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 139,'INTESA SANPAOLO BRASIL S.A','INTESA SANPAOLO BRASIL S.A',55230916,139,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 18,'BANCO TRICURY S.A','BANCO TRICURY S.A',57839805,18,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 630,'BANCO INTERCAP S.A','BANCO INTERCAP S.A',58497702,630,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 224,'BANCO FIBRA S.A','BANCO FIBRA S.A',58616418,224,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 600,'BANCO LUSO BRASILEIRO S.A','BANCO LUSO BRASILEIRO S.A',59118133,600,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 623,'BANCO PAN','BANCO PAN',59285411,623,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 204,'BANCO BRADESCO CARTOES S.A','BANCO BRADESCO CARTOES S.A',59438325,204,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 479,'BANCO ITAUBANK S.A','BANCO ITAUBANK S.A',60394079,479,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 456,'BANCO MUFG BRASIL S.A','BANCO MUFG BRASIL S.A',60498557,456,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 464,'BANCO SUMITOMO MITSUI BRASIL S.A','BANCO SUMITOMO MITSUI BRASIL S.A',60518222,464,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 613,'OMNI BANCO S.A','OMNI BANCO S.A',60850229,613,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 652,'ITAU UNIBANCO HOLDING BM S.A','ITAU UNIBANCO HOLDING BM S.A',60872504,652,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 653,'BANCO INDUSVAL S.A','BANCO INDUSVAL S.A',61024352,653,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 69,'BANCO CREFISA S.A','BANCO CREFISA S.A',61033106,69,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 370,'BANCO MIZUHO S.A','BANCO MIZUHO S.A',61088183,370,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 249,'BANCO INVESTCRED UNIBANCO S.A','BANCO INVESTCRED UNIBANCO S.A',61182408,249,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 318,'BANCO BMG S.A','BANCO BMG S.A',61186680,318,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 626,'BANCO FICSA S.A','BANCO FICSA S.A',61348538,626,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 270,'SAGITUR CC LTDA','SAGITUR CC LTDA',61444949,270,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 366,'BANCO SOCIETE GENERALE BRASIL','BANCO SOCIETE GENERALE BRASIL',61533584,366,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 113,'MAGLIANO S.A','MAGLIANO S.A',61723847,113,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 131,'TULLETT PREBON BRASIL CVC LTDA','TULLETT PREBON BRASIL CVC LTDA',61747085,131,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 11,'C.SUISSE HEDGING-GRIFFO CV S.A','C.SUISSE HEDGING-GRIFFO CV S.A',61809182,11,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 611,'BANCO PAULISTA','BANCO PAULISTA',61820817,611,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 755,'BOFA MERRILL LYNCH BM S.A','BOFA MERRILL LYNCH BM S.A',62073200,755,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 89,'CCR REG MOGIANA','CCR REG MOGIANA',62109566,89,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 643,'BANCO PINE S.A','BANCO PINE S.A',62144175,643,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 140,'EASYNVEST  TITULO CV S.A','EASYNVEST  TITULO CV S.A',62169875,140,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 707,'BANCO DAYCOVAL S.A','BANCO DAYCOVAL S.A',62232889,707,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 288,'CAROL DTVM LTDA','CAROL DTVM LTDA',62237649,288,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 101,'RENASCENCA DTVM LTDA','RENASCENCA DTVM LTDA',62287735,101,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 487,'DEUTSCHE BANK S.A BANCO ALEMAO','DEUTSCHE BANK S.A BANCO ALEMAO',62331228,487,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 233,'BANCO CIFRA','BANCO CIFRA',62421979,233,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 177,'GUIDE','GUIDE',65913436,177,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 633,'BANCO RENDIMENTO S.A','BANCO RENDIMENTO S.A',68900810,633,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 218,'BANCO BS2 S.A','BANCO BS2 S.A',71027866,218,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 292,'BS2 DISTRIBUIDORA DE TITULOS E INVESTIMENTOS','BS2 DISTRIBUIDORA DE TITULOS E INVESTIMENTOS',28650236,292,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 169,'BANCO OLE BONSUCESSO CONSIGNADO S.A','BANCO OLE BONSUCESSO CONSIGNADO S.A',71371686,169,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 293,'LASTRO RDV DTVM LTDA','LASTRO RDV DTVM LTDA',71590442,293,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 285,'FRENTE CC LTDA','FRENTE CC LTDA',71677850,285,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 80,'B&T CC LTDA','B&T CC LTDA',73622748,80,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 753,'NOVO BANCO CONTINENTAL S.A BM','NOVO BANCO CONTINENTAL S.A BM',74828799,753,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 222,'BANCO CREDIT AGRICOLE BR S.A','BANCO CREDIT AGRICOLE BR S.A',75647891,222,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 754,'BANCO SISTEMA','BANCO SISTEMA',76543115,754,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 98,'CREDIALIANCA CCR','CREDIALIANCA CCR',78157146,98,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 610,'BANCO VR S.A','BANCO VR S.A',78626983,610,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 712,'BANCO OURINVEST S.A','BANCO OURINVEST S.A',78632767,712,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 10,'CREDICOAMO','CREDICOAMO',81723108,10,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 283,'RB CAPITAL INVESTIMENTOS DTVM LTDA','RB CAPITAL INVESTIMENTOS DTVM LTDA',89960090,283,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 217,'BANCO JOHN DEERE S.A','BANCO JOHN DEERE S.A',91884981,217,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 117,'ADVANCED CC LTDA','ADVANCED CC LTDA',92856905,117,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 336,'BANCO C6 S.A  C6 BANK','BANCO C6 S.A  C6 BANK',28326000,336,5);
insert into apl_param.tab_banco (id,nome,apelido,ispb,compe, prioridade) values ( 654,'BANCO DIGIMAIS S.A','BANCO DIGIMAIS S.A',92874270,654,5);
