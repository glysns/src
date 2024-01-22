package digytal.desktop.app.model.modulo.financeiro.response.parcelamento;

public enum ParcelaBoletoStatus {
	SOLICITADO("S","Solicitado"),
    EMITIDO("E","Emitido"),
    PAGO ("P","Pago"),
    RECUSADO("R","Recursado"),
    CANCELADO("C","Cancelado");
    private String id;
    private String descricao;

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    private ParcelaBoletoStatus(String id, String descricao){
        this.id = id;
        this.descricao=descricao;

    }
    public String getUpper() {
        return descricao.toUpperCase();
    }
}
