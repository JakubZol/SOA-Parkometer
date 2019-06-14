drop table spot cascade;
drop table zone cascade;
drop table ticket cascade;
drop table worker cascade;
drop table role cascade;
drop table ticket_type cascade;

begin;

create table zone(
	zone_id SERIAL PRIMARY KEY,
	zone_name varchar(20) not null
);

create table role(
	role_id SERIAL PRIMARY KEY,
	role_name varchar(30)
);

create table ticket_type(
	type_id SERIAL PRIMARY KEY,
	time integer not null,
	price integer not null
);

create table spot(
	spot_id SERIAL PRIMARY KEY,
	spot_name varchar(10) not null,
	zone_id integer not null references zone,
	vacancy boolean not null,
	occupation_date timestamp
);

create table ticket(
	ticket_id SERIAL PRIMARY KEY,
	spot_id integer not null references spot,
	purchase_date timestamp not null,
	expiry_date timestamp not null,
	type_id integer references ticket_type
);

create table worker(
	worker_id SERIAL PRIMARY KEY,
	role_id integer not null references role,
	zone_id integer references zone,
	login varchar(30) not null,
	passwd varchar(30) not null,
	name varchar(30) not null,
	surname varchar(30) not null
);



insert into role (role_id, role_name) values
(default, 'worker'),
(default, 'admin');

insert into ticket_type (type_id, time, price) values
(default, 60, 3),
(default, 120, 5),
(default, 30, 2),
(default, 1440, 25),
(default, 2880, 40);

insert into zone (zone_id, zone_name) values
(default, 'Zone 1'),
(default, 'Zone 2'),
(default, 'Zone 3'),
(default, 'Zone 4'),
(default, 'Zone 5');

insert into worker (worker_id, role_id, zone_id, login, passwd, name, surname) values
(default, 1, 1, 'login1', 'admin1', 'Jan', 'Kowalski'),
(default, 1, 2, 'login2', 'admin2', 'Adam', 'Nowak'),
(default, 1, 3, 'login3', 'admin3', 'Mateusz', 'Wójcik'),
(default, 1, 4, 'login4', 'admin4', 'Piotr', 'Wiśniewski'),
(default, 1, 5, 'login5', 'admin5', 'Grzegorz', 'Kamiński'),
(default, 1, 1, 'login11', 'admin11', 'Jakub', 'Szymański'),
(default, 2, null, 'login6', 'admin6', 'Filip', 'Zieliński');

insert into spot (spot_id, spot_name, zone_id, vacancy, occupation_date) values
(default, 'spot11', 1, true, null),
(default, 'spot12', 1, true, null),
(default, 'spot13', 1, true, null),
(default, 'spot14', 1, true, null),
(default, 'spot15', 1, true, null),
(default, 'spot16', 1, true, null),
(default, 'spot17', 1, true, null),
(default, 'spot18', 1, true, null),
(default, 'spot19', 1, true, null),
(default, 'spot21', 2, true, null),
(default, 'spot22', 2, true, null),
(default, 'spot23', 2, true, null),
(default, 'spot24', 2, true, null),
(default, 'spot25', 2, true, null),
(default, 'spot26', 2, true, null),
(default, 'spot27', 2, true, null),
(default, 'spot28', 2, true, null),
(default, 'spot29', 2, true, null),
(default, 'spot31', 3, true, null),
(default, 'spot32', 3, true, null),
(default, 'spot33', 3, true, null),
(default, 'spot34', 3, true, null),
(default, 'spot35', 3, true, null),
(default, 'spot36', 3, true, null),
(default, 'spot37', 3, true, null),
(default, 'spot38', 3, true, null),
(default, 'spot39', 3, true, null),
(default, 'spot41', 4, true, null),
(default, 'spot42', 4, true, null),
(default, 'spot43', 4, true, null),
(default, 'spot44', 4, true, null),
(default, 'spot45', 4, true, null),
(default, 'spot46', 4, true, null),
(default, 'spot47', 4, true, null),
(default, 'spot48', 4, true, null),
(default, 'spot49', 4, true, null),
(default, 'spot51', 5, true, null),
(default, 'spot52', 5, true, null),
(default, 'spot53', 5, true, null),
(default, 'spot54', 5, true, null),
(default, 'spot55', 5, true, null),
(default, 'spot56', 5, true, null),
(default, 'spot57', 5, true, null),
(default, 'spot58', 5, true, null),
(default, 'spot59', 5, true, null);

commit;
