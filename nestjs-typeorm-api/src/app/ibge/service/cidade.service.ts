import { Injectable } from '@nestjs/common';
import { CidadeRepository } from '../repository/cidade.repository';
import { Cidade } from '../entity/cidade.entity';
import { CidadeDto } from '../dto/cidade.dto';


@Injectable()
export class CidadeService {
  constructor(private readonly repository: CidadeRepository) {}

  async create(dto: CidadeDto): Promise<Cidade> {
    
    const entity = this.repository.create({
      ...dto
    });
    console.log(entity);
    return await this.repository.save(entity);
  }

  async findAll(): Promise<Cidade[]> {
    return await this.repository.find({
      select: {
       
        nome: true
        
      },
    });
  }

  async findOne(nome: string): Promise<Cidade> {
    const entity = await this.repository.findOne({
      where: { nome: nome },
    });
    return entity;
  }
}
