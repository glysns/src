package starter.model.destination;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ps_inscricoesAlunos")
public class InscricaoAlunoDestinationEntity {
    @Id
    @Column (name="id")private Integer id ;
    @Column (name="idAluno")private Integer idAluno ;
    @Column (name="idModalidade")private Integer idModalidade ;
    @Column (name="modalidade")private String modalidade ;
    @Column (name="idCurso")private Integer idCurso ;
    @Column (name="curso")private String curso ;
    @Column (name="idEtapa")private Integer idEtapa ;
    @Column (name="etapa")private String etapa ;
    @Column (name="idTurno")private Integer idTurno ;
    @Column (name="turno")private String turno ;
    @Column (name="codINEPMunicipio")private Integer codINEPMunicipio ;
    @Column (name="municipio")private String municipio ;
    @Column (name="codINEPEntidade")private String codINEPEntidade ;
    @Column (name="entidade")private String entidade ;
    @Column (name="dataProva")private String dataProva ;
    @Column (name="turnoProva")private String turnoProva ;
    @Column (name="horaProva")private String horaProva ;
}
