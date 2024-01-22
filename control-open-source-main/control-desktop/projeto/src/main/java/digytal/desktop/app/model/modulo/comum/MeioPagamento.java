package digytal.desktop.app.model.modulo.comum;
public enum MeioPagamento {
    DINHEIRO("A","Dinheiro",true,"Din"),
    PIX("X","Pix",true,"Pix"),
    DEBITO("D","Débito",true,"Dbt"),
    BOLETO("B","Boleto",false,"Bol"),
    CREDITO("C","Crédito",false,"Crd"),
    PRAZO("Z","Prazo",false,"Prz"),
    SALDO ("Y","Saldo",true,"Sld"),
    COMPENSACAO("W","Compensação", false,"Cpc")
    ;
    private String id;
    private String descricao;
    private boolean instantaneo;
    private String sigla;
    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    private MeioPagamento(String id, String descricao, boolean instantaneo,String sigla){
        this.id = id;
        this.descricao=descricao;
        this.instantaneo=instantaneo;
        this.sigla = sigla;
    }
    public String getSigla() {
		return sigla;
	}

    public boolean isInstantaneo() {
        return instantaneo;
    }

   
    public String getUpper() {
        return descricao.toUpperCase();
    }

}
