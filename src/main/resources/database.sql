CREATE TABLE products (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL UNIQUE,
	price NUMERIC(8,2) NOT NULL,
	description VARCHAR(1000),
	user_id BIGINT,
	CONSTRAINT users_foreign_key 
	FOREIGN KEY (user_id) REFERENCES products(id)
);

CREATE TABLE categories (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE onliner_users (
	id BIGSERIAL NOT NULL PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	email VARCHAR(100) UNIQUE,
	username VARCHAR(50) NOT NULL UNIQUE,
	password VARCHAR(100) NOT NULL,
	date_of_birth DATE
);

CREATE TABLE products_categories (
	product_id BIGINT,
	categories_id BIGINT,
	CONSTRAINT products_foreign_key 
	FOREIGN KEY (product_id) REFERENCES products(id),
	CONSTRAINT categories_foreign_key 
	FOREIGN KEY (categories_id) REFERENCES categories(id)
);