package digytal.desktop.app.model.modulo.contrato;

public enum ContratoTipo {
	COMPRA("C","Compra"),
    VENDA("V","Venda"),
    LOCACAO("L","Locação"),
    SERVICO("S","Serviço");

    private String id;
    private String descricao;
    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    private ContratoTipo(String id,String descricao){
        this.id = id;
        this.descricao=descricao;
    }

    public String getUpper() {
        return descricao.toUpperCase();
    }

}	
