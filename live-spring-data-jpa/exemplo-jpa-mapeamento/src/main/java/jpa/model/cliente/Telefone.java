package jpa.model.cliente;

import javax.persistence.*;

@Entity
@Table(name = "tab_cliente_telefone")
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TelefoneTipo tipo;
    private Long numero;

    public Telefone(){

    }
    public Telefone(TelefoneTipo tipo, Long numero) {
        this.tipo = tipo;
        this.numero = numero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TelefoneTipo getTipo() {
        return tipo;
    }

    public void setTipo(TelefoneTipo tipo) {
        this.tipo = tipo;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", numero=" + numero +
                '}';
    }
}
