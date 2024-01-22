package digytal.desktop.app.model.modulo.comum.cadastramento;

public enum CadastroTipo {
	AMBOS ("Ambos"),
	CLIENTE("Cliente"),
    FORNECEDOR("Fornecedor")
    ;
    private String descricao;
    private CadastroTipo(String descricao) {
    	this.descricao = descricao;
    }
    public String getDescricao() {
		return descricao;
	}
}
