import { Column } from "typeorm"

export class SituacaoFuncionamento {
    @Column()
    id: number

    @Column()
    nome: string
}