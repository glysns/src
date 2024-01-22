alter table tab_usuario add column role varchar(20);

insert into tab_usuario (nome, login, senha,role)
values ('USER','user','$2a$10$kxEKU62QjjB.IiLQsfed4Ow/IPgDfRljp4.JgUFZq3eMPV7QP9PEu','USER');

insert into tab_usuario (nome, login, senha,role)
values ('SUPER USER','admin','$2a$10$R1NjTOvBE66hlyBpY.P1wO9zCipCIuRaT.eso.azADdTaRnq/U05S','MANAGER');
