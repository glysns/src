alter table tab_usuario add column email varchar(100);
update tab_usuario set email = 'gleyson.s@hotmail.com', role = 'MANAGER' where login = 'gleyson';

