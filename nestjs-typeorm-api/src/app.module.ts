import { Module } from '@nestjs/common';

import { TypeOrmModule } from '@nestjs/typeorm';
import { configService } from './config/config.service';
import { IbgeModule } from './app/ibge/ibge.module';

@Module({
  imports: [
    TypeOrmModule.forRoot(configService.getTypeOrmConfig()),
    IbgeModule
  ],
 
})
export class AppModule {}
