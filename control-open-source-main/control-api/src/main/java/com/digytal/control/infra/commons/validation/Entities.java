package com.digytal.control.infra.commons.validation;

public enum Entities {
    CIDADE_ENTITY("CidadeEntity", "Cidade"),
    ESTADO_UF_ENTITY("EstadoEntity", "Estado (uf)"),
    USUARIO_ENTITY("UsuarioEntity", "Usuário"),
    VENDEDOR_ENTITY("UsuarioEntity", "Vendedor"),
    MODULO_ENTITY("ModuloEntity", "Módulo"),
    FUNCIONALIDADE_ENTITY("FuncionalidadeEntity", "Funcionalidade"),
    PERFIL_ENTITY("PerfilEntity", "Perfil"),
    EMPRESA_ENTITY("EmpresaEntity", "Empresa"),
    EMPRESA_CONTA_ENTITY("ContaEntity", "Conta Empresa"),
    APLICACAO_ENTITY("AplicacaoEntity", "Aplicação"),
    CONTA_FORMA_PAGAMENTO_ENTITY("EmpresaContaFormaPagamentoEntiy", "Conta Forma Pagamento"),
    PRODUTO_ENTITY("ProdutoEntity", "Produto"),
    MARCA_ENTITY("MarcaEntity", "Marca"),
    MODELO_ENTITY("ModeloEntity", "Modelo"),
    UNIDADE_MEDIDA_ENTITY("UnidadeMedidaEntity", "Unidade de Medida"),
    CATEGORIA_ENTITY("CategoriaEntity", "Categoria"),
    CADASTRO_ENTITY("CadastroEntity", "Cadastro"),
    FORNECEDORES_ENTITY("FornecedorEntity", "Fornecedor"),
    CLIENTE_ENTITY("ClienteEntity", "Cliente"),

    ORGANIZACAO_ENTITY("OrganizacaoEntity", "Organização"),


    ESTABELECIMENTO_ENTITY("EstabelecimentoEntity", "Estabelecimento"),
    PLANO_CONTA_ENTITY("PlanoContaEntity", "Plano de Contas"),

    CONTA_BANCO_ENTITY("ContaBancoEntity", "Conta Banco"),
    PARCELA("ParcelaEntity", "Parcela"),
    PARCELAMENTO("ParcelamentoEntity", "Parcelamento"),
    LANCAMENTO("LancamentoEntity", "Lancamento"),
    ;

    private String entity;
    private String label;
    private Entities(String entity, String label){
        this.entity  =entity;
        this.label = label;
    }

    public String getEntity() {
        return entity;
    }

    public String getLabel() {
        return label;
    }
}
