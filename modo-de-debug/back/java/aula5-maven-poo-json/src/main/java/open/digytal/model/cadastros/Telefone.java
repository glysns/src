package open.digytal.model.cadastros;

public class Telefone {
    private Integer ddi;
    private Integer ddd;
    private Long numero;
    private TelefoneTipo tipo;

    public Integer getDdi() {
        return ddi;
    }

    public void setDdi(Integer ddi) {
        this.ddi = ddi;
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public TelefoneTipo getTipo() {
        return tipo;
    }

    public void setTipo(TelefoneTipo tipo) {
        this.tipo = tipo;
    }
}
