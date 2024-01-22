package starter.model.destination;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ps_cadastros")
public class CadastroDestinationEntity {
    @Id
    @Column (name="id")private Integer id ;
    @Column (name="nome")private String nome ;
    @Column (name="filiacao01")private String filiacao01 ;
    @Column (name="dataNascimento")private String dataNascimento ;
    @Column (name="sexo")private String sexo ;
    @Column (name="idEtnia")private Integer idEtnia ;
    @Column (name="cpf")private String cpf ;
    @Column (name="nis")private String nis ;
    @Column (name="cartaoSus")private String cartaoSus ;
    @Column (name="rg")private String rg ;
    @Column (name="rgOrgao")private Integer rgOrgao ;
    @Column (name="rgUf")private Integer rgUf ;
    @Column (name="rgData")private String rgData ;
    @Column (name="email")private String email ;
    @Column (name="recebeAuxilio")private String recebeAuxilio ;
    @Column (name="senha")private String senha ;
    @Column (name="idTipoUsuario")private Integer idTipoUsuario ;
    @Column (name="codigoAcesso")private String codigoAcesso ;
    @Column (name="tipoParentesco")private Integer tipoParentesco ;
    @Column (name="cepResidencia")private String cepResidencia ;
    @Column (name="logradouroResidencia")private String logradouroResidencia ;
    @Column (name="numeroResidencia")private String numeroResidencia ;
    @Column (name="complementoResidencia")private String complementoResidencia;
    @Column (name="estadoResidencia")private String estadoResidencia ;
    @Column (name="municipioResidencia")private String municipioResidencia ;
    @Column (name="bairroResidencia")private String bairroResidencia ;
    @Column (name="zonaResidencial")private String zonaResidencial ;
    @Column (name="telResidencial")private String telResidencial ;
    @Column (name="pontoReferencia")private String pontoReferencia ;
}
