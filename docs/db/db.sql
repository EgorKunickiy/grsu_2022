CREATE TABLE brand (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	name VARCHAR NOT NULL UNIQUE,
	created datetime NOT NULL,
	updated datetime NOT NULL
);

CREATE TABLE model (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	name VARCHAR NOT NULL,
	brand_id INTEGER NOT NULL REFERENCES brand(id),
	actual INTEGER NOT NULL,
	created datetime NOT NULL,
	updated datetime NOT NULL
);

CREATE TABLE car (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	owner_id INTEGER REFERENCES user_account(id),
	model_id INTEGER NOT NULL REFERENCES model(id),
	vin VARCHAR NOT NULL UNIQUE,
	created datetime NOT NULL,
	updated datetime NOT NULL
);

CREATE TABLE user_account (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	first_name VARCHAR NOT NULL,
	last_name VARCHAR NOT NULL,
	created datetime NOT NULL,
	updated datetime NOT NULL
);

