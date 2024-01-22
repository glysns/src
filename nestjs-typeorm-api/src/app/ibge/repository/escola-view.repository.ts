import { DataSource, Repository } from 'typeorm';
import { Injectable } from '@nestjs/common';
import { EscolaView } from '../entity/escola-view.entity';


@Injectable()
export class EscolaViewRepository extends Repository<EscolaView> {
  constructor(private dataSource: DataSource) {
    super(EscolaView, dataSource.createEntityManager());
  }
}
