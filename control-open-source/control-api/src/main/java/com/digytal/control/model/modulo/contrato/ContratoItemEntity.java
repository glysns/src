package com.digytal.control.model.modulo.contrato;
import com.digytal.control.model.modulo.cadastro.produto.ProdutoItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(schema = "apl_contrato", name = "tab_contrato_item")
@Data
public class ContratoItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    private Integer id;
    @Embedded
    private ProdutoItem produto;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "quant")
    private Double quantidade;
    @Column(name = "vl_unit")
    private Double valorUnitario;
    @Column(name = "vl_previsto")
    private Double valorPrevisto;
    @Column(name = "vl_aplicado")
    private Double valorAplicado;
    @Column(name = "vl_variacao")
    private Double valorVariacao;
    @Column(name = "contrato_id", insertable = false, updatable = false)
    private Integer contrato;
}
