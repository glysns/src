import { Body, Controller, Get, Post } from '@nestjs/common';
import { CidadeService } from '../service/cidade.service';
import { Cidade } from '../entity/cidade.entity';
import { CidadeDto } from '../dto/cidade.dto';
@Controller({
  path: 'cidades',
})
export class CidadeController {
  constructor(private readonly service: CidadeService) {}

  @Post()
  async create(@Body() dto: CidadeDto): Promise<CidadeDto> {
    console.log('dto', dto);
    return await this.service.create(dto);
  }

  @Get()
  async findAll(): Promise<Cidade[]> {
    return await this.service.findAll();
  }
}
