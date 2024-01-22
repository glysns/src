package open.digytal.model.cadastros;

public class Cidade {
    private String nome;
    private Uf uf;

    public void setUf(Uf uf) {
        this.uf = uf;
    }

    public Uf getUf() {
        return uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
