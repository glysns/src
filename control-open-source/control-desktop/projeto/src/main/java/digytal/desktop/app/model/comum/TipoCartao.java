package digytal.desktop.app.model.comum;
public enum TipoCartao {
	DEBITO("Débito"),
	CREDITO("Crédito");
	
	private String nome;
	private TipoCartao(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	
}
