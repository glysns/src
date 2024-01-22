import { BaseEntity, Column, Entity, PrimaryColumn, PrimaryGeneratedColumn } from "typeorm";
import { SituacaoFuncionamento } from "./situacao.funcionamento.emb.entity";

@Entity({schema: 'apl_nestjs', name: 'vw_escola'})
export class EscolaView extends BaseEntity {
  @PrimaryColumn({name: 'id_entidade'})
  id: number;

  @Column({ name:"ds_nome" })
  nome: string;

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

  @Column(() => SituacaoFuncionamento)
  situacaoFuncionamento: SituacaoFuncionamento;
  
}
