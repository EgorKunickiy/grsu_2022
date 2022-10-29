CREATE TABLE brand (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar,
	created datetime,
	updated datetime
);

CREATE TABLE model (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar,
	brand_id integer,
	actual integer,
	created datetime,
	updated datetime
);

CREATE TABLE car (
	id integer PRIMARY KEY AUTOINCREMENT,
	owner_id integer,
	model_id integer,
	vin varchar,
	created datetime,
	updated datetime
);

CREATE TABLE user_account (
	id integer PRIMARY KEY AUTOINCREMENT,
	first_name varchar,
	last_name varchar,
	created datetime,
	updated datetime
);
