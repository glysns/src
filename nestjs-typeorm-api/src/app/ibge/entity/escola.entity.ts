import { BaseEntity, Column, Entity, PrimaryColumn, PrimaryGeneratedColumn } from "typeorm";

@Entity({schema: 'apl_nestjs', name: 'tb_entidade'})
export class Escola extends BaseEntity {
  @PrimaryColumn({name: 'id_entidade'})
  id: number;

  @Column({ name:"id_grupo_entidade" })
  grupoEntidade: number;

  @Column({ name:"nr_cod_inep" })
  numeroCodigoIneo: number;

  @Column({ name:"id_situacao_funcionamento" })
  situacaoFuncionamento: number;

  @Column({ name:"ds_nome" })
  nome: string;

  @Column({ name:"nr_cod_ibge" })
  numeroCodigoIbge: number;

  @Column({ name:"id_distrito" })
  distrito: number;

  @Column({ name:"ds_cnpj" })
  cnpj: string;

  @Column({ name:"ds_cep" })
  cep: string;

  @Column({ name:"ds_logradouro" })
  logradouro: string;

  @Column({ name:"ds_numero" })
  numero: string;

  @Column({ name:"ds_complemento" })
  complemento: string;

  @Column( { name:"ds_bairro" })
  bairro: string;

  @Column( { name:"ds_ddd" })
  ddd: string;

  @Column( { name:"ds_fone" })
  fone: string;

  @Column( { name:"ds_fone2" })
  fone2: string;

  @Column({ name:'ds_email' })
  email: string;

  @Column({ name:"ds_latitude" })
  latitude: string;
  
  @Column({ name:"ds_longitude" })
  longitude: string;

  @Column({ name:"cod_gre" })
  gre: number;

  @Column({ name:"id_tipo_zona" })
  tipoZona: number;

  @Column({ name:"id_localizacao_diferenciada" })
  localizacaoDiferenciada: number;

  @Column({ name:"id_esfera_adm" })
  esferaAdm: number;

  @Column({ name:"id_entidade_vinculada" })
  entidadeVinculada: number;

  @Column({ name:"id_situacao_regulamentacao" })
  situacaoRegulamentacao: number;

  @Column({ name:"id_tipo_educacao" })
  tipoEducacao: number;

  @Column({ name:"id_escola_vinculada" })
  escolaVinculada: number;

  
}
