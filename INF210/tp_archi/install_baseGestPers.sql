

-- Attention a l'ordre dans lequel les drop sont effectues. Il faut que les tables
-- contenant des cles etrangeres sur d'autres tables soient dropees avant celles-ci 

drop table if exists personnes;
drop table if exists services;

create table services
	(id		SERIAL primary key,
	nom			VARCHAR(10));


create table personnes
	(id			SERIAL primary key,
	nom			VARCHAR(15),
	prenom			VARCHAR(15),
	telephone		VARCHAR(4),
	fonction		VARCHAR(15),
	app_serv		INTEGER NOT NULL,
	CONSTRAINT fk_personnes_app_serv FOREIGN KEY(app_serv) references services(id));

insert into services values (nextval('services_id_seq'), 'Info');
insert into personnes values(nextval('personnes_id_seq'), 'Segarra', 'Mayte', '1338', 'EC', currval('services_id_seq'));
insert into personnes values(nextval('personnes_id_seq'), 'LHostis', 'AM', '1567', 'Technicien', currval('services_id_seq'));

insert into services values (nextval('services_id_seq'), 'LUSSI');
insert into personnes values(nextval('personnes_id_seq'), 'Tanguy', 'Philippe', '1567', 'Ing', currval('services_id_seq'));