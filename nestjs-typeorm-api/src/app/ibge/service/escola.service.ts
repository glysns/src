import { Injectable } from '@nestjs/common';
import { EscolaRepository } from '../repository/escola.repository';
import { Escola } from '../entity/escola.entity';
import { EscolaDto } from '../dto/escola.dto';
import { EscolaView } from '../entity/escola-view.entity';
import { EscolaViewRepository } from '../repository/escola-view.repository';


@Injectable()
export class EscolaService {
  constructor(private readonly repository: EscolaRepository, private readonly viewRepository: EscolaViewRepository) {}

  async create(dto: EscolaDto): Promise<Escola> {
    
    const entity = this.repository.create({
      ...dto
    });
    return await this.repository.save(entity);
  }



  async findOne(nome: string): Promise<Escola> {
    const Escola = await this.repository.findOne({
      where: { nome: nome },
    });
    return Escola;
  }

  async findAll(): Promise<EscolaView[]> {
    return await this.viewRepository.find();
  }
}
