CREATE DATABASE refunits;

CREATE SCHEMA refunits_storage;

SET SEARCH_PATH = refunits_storage;

CREATE TABLE registered_user(
  id SERIAL PRIMARY KEY ,
  login CHARACTER VARYING(128) NOT NULL UNIQUE ,
  password CHARACTER VARYING(128) NOT NULL ,
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
  unit_id INTEGER ,
  option_id INTEGER ,
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
  product_id INTEGER ,
  option_id INTEGER ,
  PRIMARY KEY (product_id, option_id),
  FOREIGN KEY (option_id) REFERENCES option,
  FOREIGN KEY (product_id) REFERENCES product
);

INSERT INTO unit (name, ref_capacity, range, boiling_point)
VALUES ('AM.N10-0115-2x2EES3-K45', 11.5, 'AM', 'N10'),
       ('AM.N10-0137-2x2DES3-K45', 13.7, 'AM', 'N10'),
       ('AM.N10-0170-2x2CES4-K45', 17.0, 'AM', 'N10'),
       ('AM.N10-0184-2x2FES5-K45', 18.4, 'AM', 'N10'),
       ('AM.N10-0231-2x4EES6-K45', 23.1, 'AM', 'N10'),
       ('AM.N10-0280-2x4DES7-K45', 28.0, 'AM', 'N10'),
       ('AM.N10-0339-2x4CES9-K45', 33.9, 'AM', 'N10'),
       ('AM.N10-0349-2x4VES10-K45', 34.9, 'AM', 'N10'),
       ('AM.N10-0425-2x4TES12-K45', 42.5, 'AM', 'N10'),
       ('AM.N10-0524-3x4VES10-K45', 52.4, 'AM', 'N10'),
       ('AM.N10-0637-3x4TES12-K45', 63.7, 'AM', 'N10'),
       ('AK.N10-0161-1x4CES9-K45', 16.1, 'AK', 'N10'),
       ('AK.N10-0276-1x4NES20-K45', 27.6, 'AK', 'N10'),
       ('AKM.N35-0015-1xZF09K-K45', 1.56, 'AKM', 'N35'),
       ('AKM.N35-0027-1xZF15K-K45', 2.73, 'AKM', 'N35'),
       ('AKM.N35-0139-1xZF48K-EVI-K45', 13.9, 'AKM', 'N35');