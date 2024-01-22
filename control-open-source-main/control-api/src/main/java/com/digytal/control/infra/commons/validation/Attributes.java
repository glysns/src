package com.digytal.control.infra.commons.validation;

public enum Attributes {

    ID("id","Id"),
    UNID_EMBALAGEM("unidadeEmbalagem","Embalagem do produto"),
    CODIGO_BARRAS("codigoBarras","Código de Barras"),
    NUMERO_AUTORIZACAO("numeroAutorizacao","Número de Autorização"),
    SKU("sku","Unidade de Manutenção de Estoque"),
    NOME_ABREVIADO("nomeAbreviado","Nome abreviado"),
    USUARIO_AULA("usuarioAula","Registro Início Aula"),
    USUARIO("usuario","Usuário"),
    CPF_CNPJ("cpfCnpj","CPF / CNPJ"),
    DATA_NASCIMENTO("dataNascimento","Data de Nascimento"),
    NOME("nome","Nome"),
    QUANTIDADE("quantidade","Quantidade"),
    SIGLA("sigla","Sigla"),
    SERVICO("servico","Serviço"),
    UNIDADE_MEDIDA("unidadeMedida","Unidade Medida"),
    ATUALIZA_SALDO("atualizaSaldo","Atualiza Estoque"),
    SOBRENOME("sobrenome","Sobrenome"),
    SOBRENOME_SOCIAL("sobrenomeSocial","Sobrenome\\Social"),
    NOME_COMPLETO("nomeCompleto","Nome Completo"),
    //RAZAO_SOCIAL("razaoSocial","Razão Social"),
    NOME_FANTASIA("nomeFantasia","Nome Fantasia"),
    INSCRICAO_ESTADUAL("inscricaoEstadual","Inscrição Estadual"),
    RG_IE("rgIe","RG / Inscrição Estadual"),
    TELEFONE("telefone","Telefone"),
    WHATSAPP("whatsapp","WhatsApp"),
    EMAIL("email","E-mail"),
    ATIVIDADE_COMERCIAL("atividadeComecialProfissional","Atividade comercial ou profissional"),
    CEP("cep","Cep"),
    LOGRADOURO("logradouro","Logradouro"),
    CIDADE("cidade","Cidade"),
    LOCALIDADE("localidade","Localidade"),
    NUMERO_ENDERECO("numeroEndereco","Número de Endereço"),
    BAIRRO("bairro","Bairro"),
    COMPLEMENTO("complemento","Complemento"),
    COMPLEMENTO_ENDERECO("complementoEndereco","Complemento"),
    REFERENCIA("referencia","Referência"),
    REFERENCIA_ENDERECO("referenciaEndereco","Referência"),
    IBGE("ibge","Código IBGE"),
    ENDERECO_UF_NOME("estado","Estado"),
    ENDERECO_UF_SIGLA("uf","Sigla"),
    LOGIN("login","Login"),
    LOGIN_EMAIL("loginEmail","Login ou Email"),
    SENHA("senha","Senha"),
    SENHA_ATUAL("senhaAtual","Senha Atual"),
    NOVA_SENHA("novaSenha","Nova Senha"),
    NOVA_SENHA_CONFIRMACAO("novaSenhaConfirmacao","Nova Senha Confirmação"),

    ROLE("role","Role"),

    TIPO("tipo","Tipo"),
    EMPRESA("empresa","Empresa"),
    VALOR_DESCONTO_MANUAL("valorDescontoManul","Valor Desconto"),

    CADASTRO("cadastro","Cadastro"),
    CONTA_ORIGEM("contaOrigem","Conta Origem"),
    CONTA_DESTINO("contaDestino","Conta Destino"),
    MODULO_ID("modulo","Módulo"),
    FUNCIONALIDADE_ID("funcionalidadeId","Funcionalidade"),
    DESCRICAO("descricao","Descrição"),
    OBSERVACAO("observacao","Observação"),
    TITULO("titulo","Título"),
    PLANO_CONTA("planoConta","Plano de Conta"),
    ESTABELECIMENTO("estabelecimento","Estabelecimento"),
    EMPRESA_CONTA("empresaContaId","Conta da Empresa"),
    ESTABELECIMENTO_CONTA("estabelecimentoContaId","Conta do Estabelecimento"),
    VALOR("valor","Valor"),
    CONTA_BANCO("contaBanco","Conta Banco"),
    CONTA_AGENCIA("agencia","Número da Agência"),
    CONTA_NUMERO("numero","Número da Conta"),
    APELIDO("apelido","Apelido"),
    PERIODO_COMPETENCIA("periodoCompetencia","Período de Competência"),
    DATA_VENCIMENTO("dataVencimento","Data de Vencimento"),
    DATA_PARCELAMENTO("dataParcelamento","Data de Parcelamento"),
    DATA_PRIMEIRO_VENCTO("dataPrimeiroVencimento","Data do Primeiro Vencimento"),
    NUMERO_DOCUMENTO("numeroDocumento","Número do Documento"),
    NUMERO("numero","Número"),
    AGENCIA("agencia","Agência"),
    CONTA("conta","Conta"),
    FORMA_PAGAMENTO("formaPagamento","Forma de Pagamento"),
    MEIO_PAGAMENTO("meioPagamento","Meio de Pagamento"),

    DIAS_INTERVALO("diasIntervalo","Dias Intervalo"),
    DIA_VENCIMENTO("diaVencimento","Dia Vencimento"),
    LEGENDA("legenda","Legenda"),
    CONTA_CREDITO("contaCredito","Conta Crédito?"),
    BANCO("banco","Banco"),
    SALDO("saldo","Saldo"),
    NUMERO_PARCELAS("numeroParcelas","Número de Parcelas"),
    VALOR_TOTAL("valorTotal","Valor Total"),
    VALOR_RECEBIDO("valorRecebido","Valor Recebido"),
    DATA_AMORTIZACAO("dataAmortizacao","Data Amortização"),
    FIXO("fixo","Telefone Fixo"),
    CELULAR("celular","Telefone Celular"),
    ORGANIZACAO("organizacao","ID da Organização"),

    ESTADO_BRASILEIRO("estado","Estado Brasileiro"),
    UF("uf","Sigla Estado Brasileiro"),
    ;
    private String attribute;
    private String label;
    private Attributes(String attribute, String label){
        this.attribute  =attribute;
        this.label = label;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getLabel() {
        return label;
    }
}
