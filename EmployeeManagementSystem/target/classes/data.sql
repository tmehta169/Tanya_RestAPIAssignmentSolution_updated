insert into role values (1,'ADMIN');
insert into role values (2,'USER');

--admin -> password --admin123
--user -> password -- user123
insert into User(user_id , username , password ) values (1,'admin','$2a$12$3hRsuKqEtZbTUPsmHcHtpedjljoObIJrP.XxaCEuiBrwrGZZmCcam');
insert into User(user_id , username , password ) values (2,'user','$2a$12$b.RGKHcb6In0AZPZCAmGNutTIWZm8T4Clxt8rCC24v0HORvigeNDW');

insert into users_roles values(1,1);
insert into users_roles values(2,2);