package starter.model.destination;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ps_apuracao")
public class PSApuracaoDestinationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name="id")private Integer id ;
    @Column (name="idInscricao")private Integer idInscricao ;
    @Column (name="idEntidade")private Integer idEntidade ;
    @Column (name="preInscricao")private boolean preInscricao ;
}
