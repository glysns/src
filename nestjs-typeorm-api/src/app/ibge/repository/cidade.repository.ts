import { DataSource, Repository } from 'typeorm';
import { Injectable } from '@nestjs/common';
import { Cidade } from '../entity/cidade.entity';

@Injectable()
export class CidadeRepository extends Repository<Cidade> {
  constructor(private dataSource: DataSource) {
    super(Cidade, dataSource.createEntityManager());
  }
}
