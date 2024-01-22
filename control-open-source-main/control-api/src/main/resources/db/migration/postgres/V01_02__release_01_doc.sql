
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- MÓDULO : apl_param						DESCRIÇÃO: Módulo contendo as tabelas de parametrização do sistema
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON schema apl_param                                                                         IS 'Módulo contendo as tabelas de parametrização do sistema';

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- TABELA : apl_param.tab_banco			 DESCRIÇÃO: Tabela de instituições financeiras do sistema
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON TABLE apl_param.tab_banco                                                                IS 'Tabela contendo as informações relacionada ao banco ';

COMMENT ON COLUMN apl_param.tab_banco.id 									                         IS 'Código de controle interno, as vezes identido ao campo compe';
--COMMENT ON COLUMN apl_param.tab_banco.localiza                                                       IS 'Conteúdo igual o campo nome onde são retiradas as acentuações e definido como maiúsculo afins de consulta';
COMMENT ON COLUMN apl_param.tab_banco.compe 								                         IS 'Código de Centralizadora da Compensação de Cheques';
COMMENT ON COLUMN apl_param.tab_banco.ispb 								                         IS 'Código de Identificador de Sistema de Pagamentos Brasileiro';
COMMENT ON COLUMN apl_param.tab_banco.nome 								                         IS 'Nome original do banco';
COMMENT ON COLUMN apl_param.tab_banco.apelido 								                         IS 'Nome abreviado de identificação do banco';
COMMENT ON COLUMN apl_param.tab_banco.prioridade 							                         IS 'Número de priorização da ordem do banco na consulta em ordem crescente';

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- TABELA : apl_param.tab_ibge			         DESCRIÇÃO: Tabela de dados geográficos do Instituto Brasileiro de Geografia e Estatística (IBGE)
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON TABLE apl_param.tab_ibge                                                                 IS 'Tabela de dados geográficos do Instituto Brasileiro de Geografia e Estatística (IBGE)';

COMMENT ON COLUMN apl_param.tab_ibge.id 									                         IS 'Código do munípio com base no cadastro de IBGE';
--COMMENT ON COLUMN apl_param.tab_ibge.localiza                                                    IS 'Conteúdo igual o campo nome onde são retiradas as acentuações e definido como maiúsculo afins de consulta';
COMMENT ON COLUMN apl_param.tab_ibge.nome 								                             IS 'Nome da cidade ou município';
COMMENT ON COLUMN apl_param.tab_ibge.nome_oficial 								                     IS 'Nome oficial da cidade ou município com acentuação';
COMMENT ON COLUMN apl_param.tab_ibge.sigla 								                         IS 'Sigla da cidade ou município';
COMMENT ON COLUMN apl_param.tab_ibge.uf_id 								                         IS 'Código do estado com base no cadastro de IBGE';
COMMENT ON COLUMN apl_param.tab_ibge.uf_nome 								                         IS 'Nome do estado brasileiro';
COMMENT ON COLUMN apl_param.tab_ibge.uf_sigla 							                             IS 'Sigla do estado brasileiro';
COMMENT ON COLUMN apl_param.tab_ibge.prioridade 							                         IS 'Número de priorização da ordem do banco na consulta em ordem crescente';

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- TABELA : apl_param.tab_cep			          DESCRIÇÃO: Tabela de armazenamento de informações de endereços por CEP
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON TABLE apl_param.tab_cep                                                                  IS 'Tabela de armazenamento de informações de endereços por CEP';

COMMENT ON COLUMN apl_param.tab_cep.cep                                                             IS 'Código de Endereçamento Postal (CEP) da localidade';
--COMMENT ON COLUMN apl_param.tab_cep.localiza                                                    IS 'Conteúdo igual o campo nome onde são retiradas as acentuações e definido como maiúsculo afins de consulta';
COMMENT ON COLUMN apl_param.tab_cep.logradouro                                                      IS 'Nome do logradouro ou rua';
COMMENT ON COLUMN apl_param.tab_cep.complemento                                                     IS 'Informações adicionais sobre o endereço';
COMMENT ON COLUMN apl_param.tab_cep.bairro                                                          IS 'Nome do bairro ou região';
COMMENT ON COLUMN apl_param.tab_cep.localidade                                                      IS 'Nome da localidade ou cidade';
COMMENT ON COLUMN apl_param.tab_cep.estado                                                          IS 'Nome do estado';
COMMENT ON COLUMN apl_param.tab_cep.uf                                                              IS 'Sigla do estado';
COMMENT ON COLUMN apl_param.tab_cep.ibge                                                            IS 'Código do município no cadastro do IBGE';
COMMENT ON COLUMN apl_param.tab_cep.is_valido                                                       IS 'Indicador de validade do CEP';

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- TABELA : apl_acesso.tab_organização	         DESCRIÇÃO: Tabela de armazenamento de informações de organizações
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON TABLE apl_acesso.tab_organizacao                                                     IS 'Tabela de armazenamento de informações de organizações';

--COMMENT ON COLUMN apl_acesso.tab_organizacao.localiza                                           IS 'Conteúdo igual o campo nome onde são retiradas as acentuações e definido como maiúsculo afins de consulta';
COMMENT ON COLUMN apl_acesso.tab_organizacao.id                                                 IS 'Identificador único da organização';
COMMENT ON COLUMN apl_acesso.tab_organizacao.cpf_cnpj                                           IS 'CPF ou CNPJ da organização';
COMMENT ON COLUMN apl_acesso.tab_organizacao.nome                                               IS 'Nome da organização';
--COMMENT ON COLUMN apl_acesso.tab_organizacao.localiza                                           IS 'Conteúdo igual o campo nome onde são retiradas as acentuações e definido como maiúsculo afins de consulta';

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- MÓDULO : apl_acesso						    DESCRIÇÃO: Módulo contendo tabelas de acesso e autenticação
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON SCHEMA apl_acesso                                                                    IS 'Módulo contendo tabelas de acesso e autenticação';
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- TABELA : apl_acesso.tab_empresa	DESCRIÇÃO: Tabela de armazenamento de informações da empresa
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON TABLE apl_acesso.tab_empresa                                                         IS 'Tabela de armazenamento de informações de empresas';

COMMENT ON COLUMN apl_acesso.tab_empresa.id                                                     IS 'Identificador único da empresa';
--COMMENT ON COLUMN apl_acesso.tab_empresa.localiza                                               IS 'Conteúdo igual o campo nome onde são retiradas as acentuações e definido como maiúsculo afins de consulta';
COMMENT ON COLUMN apl_acesso.tab_empresa.cpf_cnpj                                               IS 'CPF ou CNPJ da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.nome_fantasia                                          IS 'Nome fantasia da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.sobrenome_social                                       IS 'Sobrenome social da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.dt_aniversario                                         IS 'Data de aniversário da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.rg_ie                                                  IS 'RG ou IE da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.email                                                  IS 'Endereço de email da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.end_cep                                                IS 'CEP do endereço da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.end_logradouro                                         IS 'Logradouro do endereço da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.end_numero                                             IS 'Número do endereço da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.end_complemento                                        IS 'Complemento do endereço da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.end_referencia                                         IS 'Referência do endereço da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.end_telefone                                           IS 'Telefone do endereço da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.end_bairro                                             IS 'Bairro do endereço da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.end_estado                                             IS 'Estado do endereço da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.end_cidade                                             IS 'Cidade do endereço da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.end_uf                                                 IS 'UF do endereço da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.end_ibge                                               IS 'Código IBGE do endereço da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.tel_celular                                            IS 'Telefone celular da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.tel_celular_whatsapp                                   IS 'Indicador se o telefone celular é WhatsApp (true/false)';
COMMENT ON COLUMN apl_acesso.tab_empresa.tel_fixo                                               IS 'Telefone fixo da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.organizacao_id                                         IS 'Identificador da organização associada à empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.ativ_com_prof                                          IS 'Atividade comercial/profissional da empresa';
COMMENT ON COLUMN apl_acesso.tab_empresa.is_incompleto                                          IS 'Indicador se o cadastro da empresa está incompleto (true/false)';

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- TABELA : apl_acesso.tab_usuario	DESCRIÇÃO: Tabela de armazenamento de informações de usuários
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON TABLE apl_acesso.tab_usuario                                                         IS 'Tabela de armazenamento de informações de usuários';

COMMENT ON COLUMN apl_acesso.tab_usuario.id                                                     IS 'Identificador único do usuário';
--COMMENT ON COLUMN apl_acesso.tab_usuario.localiza                                               IS 'Conteúdo igual o campo nome onde são retiradas as acentuações e definido como maiúsculo afins de consulta';
COMMENT ON COLUMN apl_acesso.tab_usuario.documento                                              IS 'Documento do usuário (CPF ou CNPJ)';
COMMENT ON COLUMN apl_acesso.tab_usuario.email                                                  IS 'Endereço de e-mail do usuário';
COMMENT ON COLUMN apl_acesso.tab_usuario.is_expirado                                            IS 'Verifica se o usuário está expirado (true/false)';
COMMENT ON COLUMN apl_acesso.tab_usuario.is_bloqueado                                           IS 'Verifica se o usuário está bloqueado (true/false)';
COMMENT ON COLUMN apl_acesso.tab_usuario.is_consultor                                           IS 'Verifica se o usuário é um consultor (true/false)';
COMMENT ON COLUMN apl_acesso.tab_usuario.login                                                  IS 'Nome de login do usuário';
COMMENT ON COLUMN apl_acesso.tab_usuario.nome                                                   IS 'Nome do usuário';
COMMENT ON COLUMN apl_acesso.tab_usuario.sobrenome                                              IS 'Sobrenome do usuário';
COMMENT ON COLUMN apl_acesso.tab_usuario.senha                                                  IS 'Senha do usuário';
COMMENT ON COLUMN apl_acesso.tab_usuario.cadastro_id                                            IS 'Identificador do cadastro do usuário';
COMMENT ON COLUMN apl_acesso.tab_usuario.cadastro_id                                            IS 'Identificador do cadastro associado ao usuário';


-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- TABELA : apl_acesso.tab_conta	DESCRIÇÃO: Tabela de armazenamento da conta da empresa
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON TABLE apl_acesso.tab_conta                                                   IS 'Tabela de armazenamento de contas de empresas';

COMMENT ON COLUMN apl_acesso.tab_conta.id                                               IS 'Identificador único da conta de empresa';
--COMMENT ON COLUMN apl_acesso.tab_conta.localiza                                         IS 'Conteúdo igual o campo nome onde são retiradas as acentuações e definido como maiúsculo afins de consulta';
COMMENT ON COLUMN apl_acesso.tab_conta.agencia                                          IS 'Agência da conta';
COMMENT ON COLUMN apl_acesso.tab_conta.numero                                           IS 'Número da conta';
COMMENT ON COLUMN apl_acesso.tab_conta.saldo                                            IS 'Saldo da conta';
COMMENT ON COLUMN apl_acesso.tab_conta.descricao                                        IS 'Descrição da conta';
COMMENT ON COLUMN apl_acesso.tab_conta.legenda                                          IS 'Legenda da conta';
COMMENT ON COLUMN apl_acesso.tab_conta.chave_pix                                        IS 'Chave PIX da conta';
--COMMENT ON COLUMN apl_acesso.tab_conta.is_conta_padrao                                  IS 'Indicador se é a conta padrão (true/false)';
COMMENT ON COLUMN apl_acesso.tab_conta.is_conta_credito                                 IS 'Indicador se é uma conta de crédito (true/false)';
COMMENT ON COLUMN apl_acesso.tab_conta.banco_id                                         IS 'Identificador do banco associado à conta';
COMMENT ON COLUMN apl_acesso.tab_conta.empresa_id                                       IS 'Identificador da empresa associada à conta';
COMMENT ON COLUMN apl_acesso.tab_conta.dia_vencimento                                   IS 'Dia de vencimento da conta';
COMMENT ON COLUMN apl_acesso.tab_conta.dias_intervalo                                   IS 'Dias de intervalo da conta';

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- TABELA : apl_acesso.tab_aplicação	DESCRIÇÃO: Tabela de associação entre empresas, contas e aplicações
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON TABLE apl_acesso.tab_aplicacao                                               IS 'Tabela de associação entre empresas, contas e aplicações';

COMMENT ON COLUMN apl_acesso.tab_aplicacao.id                                           IS 'Identificador da aplicação';
--COMMENT ON COLUMN apl_acesso.tab_aplicacao.localiza                                       IS 'Conteúdo igual o campo nome onde são retiradas as acentuações e definido como maiúsculo afins de consulta';
COMMENT ON COLUMN apl_acesso.tab_aplicacao.nome                                         IS 'Nome da aplicação';
COMMENT ON COLUMN apl_acesso.tab_aplicacao.tipo                                         IS 'Identificar o tipo aplicação';
COMMENT ON COLUMN apl_acesso.tab_aplicacao.is_area                                      IS 'Identificar a área da aplicação';
COMMENT ON COLUMN apl_acesso.tab_aplicacao.is_natureza                                  IS 'Identificar qual a naturaza da aplicação';
COMMENT ON COLUMN apl_acesso.tab_aplicacao.is_principal                                 IS 'Verifica se a aplicação é a principal';
COMMENT ON COLUMN apl_acesso.tab_aplicacao.organizacao_id                               IS 'Identificador da organização';



-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- TABELA : apl_acesso.tab_forma_pagamento	DESCRIÇÃO: Tabela de associação entre empresas, contas e formas de pagamento
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON TABLE apl_acesso.tab_forma_pagamento                                        IS 'Tabela de associação entre empresas, contas e formas de pagamento';

COMMENT ON COLUMN apl_acesso.tab_forma_pagamento.id                                    IS 'Identificador único da associação entre empresa, conta e forma de pagamento';
COMMENT ON COLUMN apl_acesso.tab_forma_pagamento.nr_parcelas                           IS 'Numero de parcelas do pagamento';
COMMENT ON COLUMN apl_acesso.tab_forma_pagamento.empresa_id                            IS 'Identificador da empresa relacionada à associação';
COMMENT ON COLUMN apl_acesso.tab_forma_pagamento.conta_id                              IS 'Identificador da conta da empresa relacionada à associação';
COMMENT ON COLUMN apl_acesso.tab_forma_pagamento.meio_pagto                            IS 'Forma de pagamento associada';
COMMENT ON COLUMN apl_acesso.tab_forma_pagamento.taxa                                  IS 'Taxa associada à forma de pagamento';

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- TABELA : apl_acesso.tab_usuario_empresa	DESCRIÇÃO: Tabela de associação entre usuários e empresas
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON TABLE apl_acesso.tab_usuario_empresa                                                 IS 'Tabela de associação entre usuários e empresas';

COMMENT ON COLUMN apl_acesso.tab_usuario_empresa.usuario_id                                     IS 'Identificador do usuário associado';
COMMENT ON COLUMN apl_acesso.tab_usuario_empresa.empresa_id                                     IS 'Identificador da empresa associada';
COMMENT ON COLUMN apl_acesso.tab_usuario_empresa.is_padrao                                      IS 'Inditificando se é a empresa padrão para o usuário (true/false)';

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- MÓDULO : apl_cadastro						DESCRIÇÃO: Módulo contendo tabelas de cadastros de entidades, como clientes, fornecedores e produtos
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON SCHEMA apl_cadastro                                                                  IS 'Módulo contendo tabelas para registrar lançamentos contábeis, parcelamentos, pagamentos e movimentações financeiras';

COMMENT ON TABLE apl_cadastro.tab_cadastro                                                      IS 'Tabela de armazenamento de informações de cadastros';

COMMENT ON COLUMN apl_cadastro.tab_cadastro.id                                                  IS 'Identificador único do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.localiza                                            IS 'Conteúdo igual o campo nome onde são retiradas as acentuações e definido como maiúsculo afins de consulta';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.cpf_cnpj                                            IS 'CPF ou CNPJ do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.nome_fantasia                                       IS 'Nome fantasia do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.sobrenome_social                                    IS 'Sobrenome social do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.dt_aniversario                                      IS 'Data de aniversário do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.rg_ie                                               IS 'RG ou Inscrição Estadual do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.email                                               IS 'Endereço de e-mail do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.end_cep                                             IS 'CEP do endereço do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.end_logradouro                                      IS 'Logradouro do endereço do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.end_numero                                          IS 'Número do endereço do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.end_complemento                                     IS 'Complemento do endereço do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.end_referencia                                      IS 'Referência do endereço do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.end_telefone                                        IS 'Telefone do endereço do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.end_bairro                                          IS 'Bairro do endereço do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.end_estado                                          IS 'Estado do endereço do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.end_cidade                                          IS 'Cidade do endereço do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.end_uf                                              IS 'UF do endereço do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.end_ibge                                            IS 'IBGE do endereço do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.tel_celular                                         IS 'Telefone celular do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.tel_celular_whatsapp                                IS 'Indicar se o telefone celular é WhatsApp (true/false)';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.tel_fixo                                            IS 'Telefone fixo do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.is_cliente                                          IS 'Indicar se é cliente (true/false)';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.is_fornecedor                                       IS 'Indicar se é fornecedor (true/false)';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.ativ_com_prof                                       IS 'Atividade comercial/profissional do cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.organizacao_id                                      IS 'Identificador da organização associada ao cadastro';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.is_incompleto                                       IS 'Indicar se o cadastro está incompleto (true/false)';
COMMENT ON COLUMN apl_cadastro.tab_cadastro.integra_asaas_id                                    IS 'Identificador de integração ao acesso da associação';
--COMMENT ON COLUMN apl_cadastro.tab_cadastro.cod_integracao                                      IS 'Código de integração de cadastro';

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--TABELA : apl_cadastro.tab_marca	DESCRIÇÃO: Tabela de armazenamento de informações da marca
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON TABLE apl_cadastro.tab_marca                                                          IS 'Tabela de armazenamento de informações de marca';
COMMENT ON COLUMN apl_cadastro.tab_marca.nome                                                    IS 'Nome do marca';
COMMENT ON COLUMN apl_cadastro.tab_marca.nome_abreviado                                        IS 'Nome da marca abreviado';
--COMMENT ON COLUMN apl_cadastro.tab_marca.localiza                                           IS 'Conteúdo igual o campo nome onde são retiradas as acentuações e definido como maiúsculo afins de consulta';
COMMENT ON COLUMN apl_cadastro.tab_marca.sigla                                                   IS 'Sigla relacionada a marca';
COMMENT ON COLUMN apl_cadastro.tab_marca.is_excluido                                             IS 'Verifica se está excluido';
COMMENT ON COLUMN apl_cadastro.tab_marca.id                                                      IS 'Identificador único da marca';
COMMENT ON COLUMN apl_cadastro.tab_marca.organizacao_id                                          IS 'Identificador da organização relacionada a marca';

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--TABELA : apl_cadastro.tab_marca	DESCRIÇÃO: Tabela de armazenamento de informações da marca
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON TABLE apl_cadastro.tab_categoria                                                          IS 'Tabela de armazenamento e informações da categoria';
COMMENT ON COLUMN apl_cadastro.tab_categoria.id                                                      IS 'Identificador único da categoria';
COMMENT ON COLUMN apl_cadastro.tab_categoria.nome                                                    IS 'Nome da categoria';
COMMENT ON COLUMN apl_cadastro.tab_categoria.nome_abreviado                                            IS 'Nome da categoria abreviada';
COMMENT ON COLUMN apl_cadastro.tab_categoria.localiza                                               IS 'Conteúdo igual o campo nome onde são retiradas as acentuações e definido como maiúsculo afins de consulta';
COMMENT ON COLUMN apl_cadastro.tab_categoria.is_excluido                                             IS 'Verifica se está excluido';
COMMENT ON COLUMN apl_cadastro.tab_categoria.organizacao_id                                          IS 'Identificador da organização relacionada a categoria';

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--TABELA : apl_cadastro.tab_modelo	DESCRIÇÃO: Tabela de armazenamento de informações da modelo
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON TABLE apl_cadastro.tab_modelo                                                          IS 'Tabela de armazenamento de informações do modelo';
COMMENT ON COLUMN apl_cadastro.tab_modelo.id                                                      IS 'Identificador único do modelo';
COMMENT ON COLUMN apl_cadastro.tab_modelo.nome                                                    IS 'Nome do modelo';
COMMENT ON COLUMN apl_cadastro.tab_modelo.nome_abreviado                                          IS 'Nome do modelo abreviado';
COMMENT ON COLUMN apl_cadastro.tab_modelo.is_excluido                                             IS 'Verifica se está excluido';
COMMENT ON COLUMN apl_cadastro.tab_modelo.localiza                                                IS 'Conteúdo igual o campo nome onde são retiradas as acentuações e definido como maiúsculo afins de consulta';
COMMENT ON COLUMN apl_cadastro.tab_modelo.sigla                                                   IS 'Sigla relacionada ao modleo';
COMMENT ON COLUMN apl_cadastro.tab_modelo.organizacao_id                                          IS 'Identificador da organização relacionada ao modelo';

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--TABELA : apl_cadastro.tab_und_medida	DESCRIÇÃO: Tabela de armazenamento de informações das unidades de medida
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON TABLE apl_cadastro.tab_unid_medida                                                     IS 'Tabela de armazenamento de informações das unidades de medida';
COMMENT ON COLUMN apl_cadastro.tab_unid_medida.id                                                 IS 'Identificador único da unidade de medida';
COMMENT ON COLUMN apl_cadastro.tab_unid_medida.sigla                                              IS 'Sigla definida manualmente, podendo ser identico ao id';
COMMENT ON COLUMN apl_cadastro.tab_unid_medida.nome                                               IS 'Nome ou identificação';
COMMENT ON COLUMN apl_cadastro.tab_unid_medida.localiza                                           IS 'Conteúdo igual o campo nome onde são retiradas as acentuações e definido como maiúsculo afins de consulta';
COMMENT ON COLUMN apl_cadastro.tab_unid_medida.descricao                                          IS 'Descrição ou informações adicionais';
COMMENT ON COLUMN apl_cadastro.tab_unid_medida.conteudo                                           IS 'Conteúdo ou capacidade definida';
COMMENT ON COLUMN apl_cadastro.tab_unid_medida.is_embalagem                                       IS 'Determina se a unidade de medida é considerada embalagem';
COMMENT ON COLUMN apl_cadastro.tab_unid_medida.is_excluido                                        IS 'Determina uma exclusão lógica do registro';
COMMENT ON COLUMN apl_cadastro.tab_unid_medida.organizacao_id                                     IS 'Identificador da organização ao qual o registro está vinculado';

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- TABELA : apl_cadastro.tab_produto	DESCRIÇÃO: Tabela de armazenamento de informações de produtos
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON TABLE apl_cadastro.tab_produto                                                          IS 'Tabela de armazenamento de informações de produtos';

COMMENT ON COLUMN apl_cadastro.tab_produto.id                                                      IS 'Identificador único do produto';
--COMMENT ON COLUMN apl_cadastro.tab_produto.localiza                                                IS 'Conteúdo igual o campo nome onde são retiradas as acentuações e definido como maiúsculo afins de consulta';
COMMENT ON COLUMN apl_cadastro.tab_produto.nome                                                    IS 'Nome do produto';
COMMENT ON COLUMN apl_cadastro.tab_produto.is_excluido                                             IS 'Verifica se está excluido';
COMMENT ON COLUMN apl_cadastro.tab_produto.nome_abreviado                                          IS 'Nome do produto abreviado';
COMMENT ON COLUMN apl_cadastro.tab_produto.cod_barras                                              IS 'Código de barras do produto';
COMMENT ON COLUMN apl_cadastro.tab_produto.sku                                                     IS 'SKU (unidade de manutenção de estoque) do produto';
COMMENT ON COLUMN apl_cadastro.tab_produto.vl_produto                                              IS 'Valor do produto';
COMMENT ON COLUMN apl_cadastro.tab_produto.tx_liquidacao                                           IS 'Valor da taxa  de liquidação do produto';
COMMENT ON COLUMN apl_cadastro.tab_produto.saldo                                                   IS 'Saldo atual do produto';
COMMENT ON COLUMN apl_cadastro.tab_produto.is_servico                                              IS 'Indicar se é um produto/serviço (true/false)';
COMMENT ON COLUMN apl_cadastro.tab_produto.is_principal                                            IS 'Indicar se é um produto principal (true/false)';
COMMENT ON COLUMN apl_cadastro.tab_produto.is_atualiza_saldo                                       IS 'Indicar se atualizou saldo (true/false)';
COMMENT ON COLUMN apl_cadastro.tab_produto.is_interno                                              IS 'Indicar se é um produto interno (true/false)';
COMMENT ON COLUMN apl_cadastro.tab_produto.app_is_visivel                                          IS 'Indicar  se app está visível (true/false)';
COMMENT ON COLUMN apl_cadastro.tab_produto.app_ordem_visualizacao                                  IS 'Identificar a ordem de visualização';
COMMENT ON COLUMN apl_cadastro.tab_produto.marca_id                                                IS 'Indicador da marca associado ao produto';
COMMENT ON COLUMN apl_cadastro.tab_produto.modelo_id                                               IS 'Identificador do modelo associado ao produto';
COMMENT ON COLUMN apl_cadastro.tab_produto.categoria_id                                            IS 'Identificador da categoria associado ao produto';
COMMENT ON COLUMN apl_cadastro.tab_produto.unid_medida_id                                             IS 'Unidade de medida do produto';
COMMENT ON COLUMN apl_cadastro.tab_produto.organizacao_id                                          IS 'Identificador da organização associado ao produto';

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- MÓDULO : apl_contrato						DESCRIÇÃO: Módulo contendo tabelas com detalhes e informações sobre contratos e suas transações
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON SCHEMA apl_contrato                                                                      IS 'Módulo contendo tabelas com detalhes e informações sobre contratos e suas transações';
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- TABELA : apl_contrato.tab_contrato	DESCRIÇÃO: Tabela de armazenamento de informações de contratos
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON TABLE apl_contrato.tab_contrato                                                          IS 'Tabela de armazenamento de informações de contratos';

COMMENT ON COLUMN apl_contrato.tab_contrato.id                                                      IS 'Identificador único do contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato.descricao                                               IS 'Descrição do contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato.cpt_ano                                                 IS 'Ano do contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato.cpt_competencia                                     IS 'Período de competência do contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato.cpt_data                                           IS 'Data e hora do lançamento do contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato.vl_previsto                                             IS 'Valor previsto do contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato.vl_aplicado                                             IS 'Valor aplicado do contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato.vl_acres_pagto                                            IS 'Valor de acréscimo do contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato.vl_acres_itens                                            IS 'Valor de acréscimo do contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato.vl_desc_itens                                           IS 'Valorde de desconto do item do contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato.vl_desc_manual                                          IS 'Valorde de desconto do item manual do contrato';
--COMMENT ON COLUMN apl_contrato.tab_contrato.vl_desconto IS 'Valor de desconto do contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato.cpt_dia                                                 IS 'Dia do contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato.cpt_mes                                                 IS 'Mês do contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato.cpt_periodo                                          IS 'Período de lançamento do contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato.part_cadastro_id                                        IS 'Identificador do cadastro da parte envolvida';
COMMENT ON COLUMN apl_contrato.tab_contrato.part_empresa_id                                         IS 'Identificador da empresa da parte envolvida';
COMMENT ON COLUMN apl_contrato.tab_contrato.part_usuario_id                                         IS 'Identificador da empresa da parte usuário';
COMMENT ON COLUMN apl_contrato.tab_contrato.part_organizacao_id                                     IS 'Identificador da organização da parte envolvida';
COMMENT ON COLUMN apl_contrato.tab_contrato.user_intermediador_id                                   IS 'Identificador do usuário da parte envolvida';
COMMENT ON COLUMN apl_contrato.tab_contrato.sit_contrato                                            IS 'Situação do contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato.tipo                                                    IS 'Tipo do contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato.vigencia_inicio                                         IS 'Vigência de início do contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato.vigencia_fim                                            IS 'Vigência de fim do contrato';

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- TABELA : apl_contrato.tab_contrato_item	       DESCRIÇÃO: Tabela de armazenamento de itens de contrato
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
COMMENT ON TABLE apl_contrato.tab_contrato_item                                                     IS 'Tabela de armazenamento de itens de contrato';

COMMENT ON COLUMN apl_contrato.tab_contrato_item.id                                                 IS 'Identificador único do item de contrato';
COMMENT ON COLUMN apl_contrato.tab_contrato_item.prod_id                                            IS 'Identificador do produto associado ao item';
COMMENT ON COLUMN apl_contrato.tab_contrato_item.descricao                                          IS 'Nome do produto ou descricao do servico associado ao item';
COMMENT ON COLUMN apl_contrato.tab_contrato_item.prod_und_med_sigla                                 IS 'Sigla unidade de medida do produto associado ao item';
COMMENT ON COLUMN apl_contrato.tab_contrato_item.prod_cod_barras                                    IS 'Código de barras do produto associado ao item';
COMMENT ON COLUMN apl_contrato.tab_contrato_item.prod_preco                                         IS 'Valor do produto associado ao item';
COMMENT ON COLUMN apl_contrato.tab_contrato_item.prod_saldo                                         IS 'Saldo do produto associado ao item';
COMMENT ON COLUMN apl_contrato.tab_contrato_item.quant                                              IS 'Quantidade do item';
COMMENT ON COLUMN apl_contrato.tab_contrato_item.vl_unit                                            IS 'Valor unitário do item';
COMMENT ON COLUMN apl_contrato.tab_contrato_item.vl_previsto                                        IS 'Valor previsto do item baseado na multiplicacao entre vl_unit e quant';
COMMENT ON COLUMN apl_contrato.tab_contrato_item.vl_aplicado                                        IS 'Valor aplicado do item';
COMMENT ON COLUMN apl_contrato.tab_contrato_item.vl_variacao                                        IS 'Valor de variação da negociacao do item';
COMMENT ON COLUMN apl_contrato.tab_contrato_item.contrato_id                                        IS 'Identificador do contrato associado ao item';
