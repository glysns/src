import { DataSource, Repository } from 'typeorm';
import { Injectable } from '@nestjs/common';
import { Escola } from '../entity/escola.entity';

@Injectable()
export class EscolaRepository extends Repository<Escola> {
  constructor(private dataSource: DataSource) {
    super(Escola, dataSource.createEntityManager());
  }
}
