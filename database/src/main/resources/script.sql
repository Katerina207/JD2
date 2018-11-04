CREATE DATABASE refunits;

CREATE SCHEMA refunits_storage;

SET SEARCH_PATH = refunits_storage;

CREATE TABLE registered_user(
  id SERIAL PRIMARY KEY ,
  login CHARACTER VARYING(128),
  password CHARACTER VARYING(128),
  registration_date DATE NOT NULL
);

CREATE TABLE admin(
  registrated_user_id SERIAL PRIMARY KEY ,
  FOREIGN KEY (registrated_user_id) REFERENCES registered_user
);

CREATE TABLE consumer(
  registrated_user_id SERIAL PRIMARY KEY ,
  company CHARACTER VARYING(256) NOT NULL ,
  contact_person CHARACTER VARYING(256),
  mail CHARACTER VARYING(256),
  telephone CHARACTER VARYING(128),
  FOREIGN KEY (registrated_user_id) REFERENCES registered_user
);

CREATE TABLE manager(
  registrated_user_id SERIAL PRIMARY KEY ,
  company CHARACTER VARYING(256) NOT NULL ,
  contact_person CHARACTER VARYING(256),
  FOREIGN KEY (registrated_user_id) REFERENCES registered_user
);

CREATE TABLE option(
  id SERIAL PRIMARY KEY ,
  name CHARACTER VARYING(16) NOT NULL ,
  price INTEGER NOT NULL ,
  description CHARACTER VARYING(1024),
  UNIQUE (name, price)
);

CREATE TABLE unit(
  id SERIAL PRIMARY KEY ,
  name CHARACTER VARYING(256) NOT NULL UNIQUE ,
  ref_capacity NUMERIC(6, 2) NOT NULL ,
  range CHARACTER VARYING(16),
  boiling_point CHARACTER VARYING(16),
  weight INTEGER,
  length INTEGER,
  width INTEGER,
  height INTEGER,
  description CHARACTER VARYING(1024),
  price INTEGER
);

CREATE TABLE unit_option(
  unit_id INTEGER NOT NULL ,
  option_id INTEGER NOT NULL ,
  PRIMARY KEY (unit_id, option_id),
  FOREIGN KEY (unit_id) REFERENCES unit,
  FOREIGN KEY (option_id) REFERENCES option
);

CREATE TABLE pre_order(
  id SERIAL PRIMARY KEY ,
  registrated_user_id INTEGER NOT NULL ,
  date DATE NOT NULL ,
  status CHARACTER VARYING(128),
  comment CHARACTER VARYING(512),
  FOREIGN KEY (registrated_user_id) REFERENCES registered_user
);

CREATE TABLE product(
  id SERIAL PRIMARY KEY ,
  pre_order_id INTEGER,
  unit_id INTEGER,
  number INTEGER,
  sum INTEGER,
  FOREIGN KEY (pre_order_id) REFERENCES pre_order,
  FOREIGN KEY (unit_id) REFERENCES unit
);

CREATE TABLE product_option(
  product_id INTEGER NOT NULL ,
  option_id INTEGER NOT NULL ,
  PRIMARY KEY (product_id, option_id),
  FOREIGN KEY (option_id) REFERENCES option,
  FOREIGN KEY (product_id) REFERENCES product
);