import { Body, Controller, Get, Post } from '@nestjs/common';
import { EscolaService } from '../service/escola.service';
import { EscolaDto } from '../dto/escola.dto';
import { EscolaView } from '../entity/escola-view.entity';

@Controller({
  path: 'escolas',
})
export class EscolaControllerV1 {
  constructor(private readonly service: EscolaService) {}

  @Post()
  async create(@Body() dto: EscolaDto): Promise<EscolaDto> {
    return await this.service.create(dto);
  }

  @Get()
  async findAll(): Promise<EscolaView[]> {
    return await this.service.findAll();
  }
}
