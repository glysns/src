import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity({name: 'tb_cidade'})
export class Cidade  {
  @PrimaryGeneratedColumn({name: 'id'})
  id: number;

  @Column({ name:"nome" })
  nome: string;
}