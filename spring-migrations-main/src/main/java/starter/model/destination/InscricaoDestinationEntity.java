package starter.model.destination;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ps_inscricoes")
public class InscricaoDestinationEntity {
    @Id
    @Column (name="id")private Integer id ;
    @Column (name="idResponsavel")private Integer idResponsavel ;
    @Column (name="nome")private String nome ;
    @Column (name="nomeSocial")private String nomeSocial ;
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
    @Column (name="cartaoVacina")private String cartaoVacina ;
    @Column (name="nacionalidade")private String nacionalidade ;
    @Column (name="estado")private Integer estado ;
    @Column (name="naturalidade")private Integer naturalidade ;
    @Column (name="idDeficiencia")private Integer idDeficiencia ;
    @Column (name="idTranstorno")private Integer idTranstorno ;
    @Column (name="superDotado")private String superDotado ;
    @Column (name="zonaMoradia")private String zonaMoradia ;
    @Column (name="cepResidencia")private String cepResidencia ;
    @Column (name="logradouroResidencia")private String logradouroResidencia ;
    @Column (name="numeroResidencia")private String numeroResidencia ;
    @Column (name="estadoResidencia")private Integer estadoResidencia ;
    @Column (name="municipioResidencia")private Integer municipioResidencia ;
    @Column (name="bairroResidencia")private String bairroResidencia ;
    @Column (name="complementoResidencial")private String complementoResidencial ;
    @Column (name="telResidencial")private String telResidencial ;
    @Column (name="telCelular")private String telCelular ;
    @Column (name="telComercial")private String telComercial ;
    @Column (name="rendaFamiliar")private Integer rendaFamiliar ;
    @Column (name="profissaoFiliacao01")private Integer profissaoFiliacao01 ;
    @Column (name="filiacao02")private String filiacao02 ;
    @Column (name="profissaoFiliacao02")private Integer profissaoFiliacao02 ;
    @Column (name="finalizado")private String finalizado ;
    @Column (name="hash")private String hash ;
    @Column (name="inscricaoFinalizada")private String inscricaoFinalizada ;
    @Column (name="povoTradicional")private String povoTradicional ;
    @Column (name="tipoPovoTradiciona")private Integer tipoPovoTradiciona ;
    @Column (name="tipoPovoTradicionalOutro")private String tipoPovoTradicionalOutro ;
    @Column (name="tipoSanguineo")private String tipoSanguineo ;
    @Column (name="possueDeficiencia")private String possueDeficiencia ;
    @Column (name="nomeEtnia")private String nomeEtnia ;
    @Column (name="nomeEtniaOutra")private String nomeEtniaOutra ;
    @Column (name="idPovoTradicional")private Integer idPovoTradicional ;
    @Column (name="idPovoTradicionalOutra")private String idPovoTradicionalOutra ;
    @Column (name="deficiencia1")private String deficiencia1 ;
    @Column (name="deficiencia2")private String deficiencia2 ;
    @Column (name="deficiencia3")private String deficiencia3 ;
    @Column (name="deficiencia4")private String deficiencia4 ;
    @Column (name="pais")private String pais ;
    @Column (name="pontoReferencia")private String pontoReferencia ;
    @Column (name="localizacaoDiferenciada")private Integer localizacaoDiferenciada ;
    @Column (name="comunidade")private String comunidade ;
    @Column (name="idTipoFiliacao1")private Integer idTipoFiliacao1 ;
    @Column (name="cpfFiliacao1")private String cpfFiliacao1 ;
    @Column (name="nomeFiliacao1")private String nomeFiliacao1 ;
    @Column (name="idTipoRepresentanteLegal")private Integer idTipoRepresentanteLegal ;
    @Column (name="cpfRepresentanteLegal")private String cpfRepresentanteLegal ;
    @Column (name="nomeRepresentanteLegal")private String nomeRepresentanteLegal ;
    @Column (name="cepRepresentanteLegal")private String cepRepresentanteLegal ;
    @Column (name="logradouroRepresentanteLegal")private String logradouroRepresentanteLegal ;
    @Column (name="numeroRepresentanteLegal")private String numeroRepresentanteLegal ;
    @Column (name="bairroRepresentanteLegal")private String bairroRepresentanteLegal ;
    @Column (name="complementoRepresentanteLegal")private String complementoRepresentanteLegal ;
    @Column (name="municipioRepresentanteLegal")private String municipioRepresentanteLegal ;
    @Column (name="zonaMoradiaRepresentanteLegal")private String zonaMoradiaRepresentanteLegal ;
    @Column (name="estadoRepresentanteLegal")private Integer estadoRepresentanteLegal ;
    @Column (name="idTipoFiliacao2")private Integer idTipoFiliacao2 ;
    @Column (name="cpfFiliacao2")private String cpfFiliacao2 ;
    @Column (name="nomeFiliacao2")private String nomeFiliacao2 ;
}
