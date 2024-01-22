package digytal.desktop.app.model.modulo.acesso.empresa.aplicacao;

public enum AplicacaoTipo {
	RECEITA("R","Receita"),
    DESPESA("D","Despesa");
    private String id;
    private String descricao;
    private AplicacaoTipo(String id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }
    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUpper() {
        return descricao.toUpperCase();
    }

}
