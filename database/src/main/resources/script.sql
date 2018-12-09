CREATE DATABASE refunits;

CREATE SCHEMA refunits_storage;

SET SEARCH_PATH = refunits_storage;

CREATE TABLE registered_user (
  id                SERIAL PRIMARY KEY,
  login             CHARACTER VARYING(128) NOT NULL UNIQUE,
  password          CHARACTER VARYING(128) NOT NULL,
  registration_date DATE                   NOT NULL,
  company           CHARACTER VARYING(256) NOT NULL,
  contact_person    CHARACTER VARYING(256),
  mail              CHARACTER VARYING(256),
  telephone         CHARACTER VARYING(128),
  role              CHARACTER VARYING(64)  NOT NULL
);

CREATE TABLE option (
  id          SERIAL PRIMARY KEY,
  name        CHARACTER VARYING(16) NOT NULL,
  price       INTEGER               NOT NULL,
  description CHARACTER VARYING(1024),
  UNIQUE (name, price)
);

CREATE TABLE unit (
  id            SERIAL PRIMARY KEY,
  name          CHARACTER VARYING(256) NOT NULL UNIQUE,
  ref_capacity  NUMERIC(6, 2)          NOT NULL,
  range         CHARACTER VARYING(16),
  boiling_point CHARACTER VARYING(16),
  weight        INTEGER,
  length        INTEGER,
  width         INTEGER,
  height        INTEGER,
  description   CHARACTER VARYING(1024),
  price         INTEGER NOT NULL
);

CREATE TABLE unit_option (
  unit_id   INTEGER,
  option_id INTEGER,
  PRIMARY KEY (unit_id, option_id),
  FOREIGN KEY (unit_id) REFERENCES unit,
  FOREIGN KEY (option_id) REFERENCES option
);

CREATE TABLE pre_order (
  id                 SERIAL PRIMARY KEY,
  registered_user_id INTEGER NOT NULL,
  date               DATE    NOT NULL,
  status             CHARACTER VARYING(128),
  comment            CHARACTER VARYING(512),
  FOREIGN KEY (registered_user_id) REFERENCES registered_user
);

CREATE TABLE productTemp (
  id           SERIAL PRIMARY KEY,
  pre_order_id INTEGER,
  unit_id      INTEGER,
  number       INTEGER,
  sum          INTEGER,
  FOREIGN KEY (pre_order_id) REFERENCES pre_order,
  FOREIGN KEY (unit_id) REFERENCES unit
);

CREATE TABLE product_option (
  product_id INTEGER,
  option_id  INTEGER,
  PRIMARY KEY (product_id, option_id),
  FOREIGN KEY (option_id) REFERENCES option,
  FOREIGN KEY (product_id) REFERENCES productTemp
);

DROP TABLE product_option;
DROP TABLE productTemp;
DROP TABLE pre_order;
DROP TABLE registered_user;

DROP TABLE unit_option;
DROP TABLE option;
DROP TABLE unit;

INSERT INTO unit (name, ref_capacity, range, boiling_point, price)
VALUES ('AM.N10-0115-2x2EES3-K45', 11.5, 'AM', 'N10', 2300),
       ('AM.N10-0137-2x2DES3-K45', 13.7, 'AM', 'N10', 2450),
       ('AM.N10-0170-2x2CES4-K45', 17.0, 'AM', 'N10', 2610),
       ('AM.N10-0184-2x2FES5-K45', 18.4, 'AM', 'N10', 2780),
       ('AM.N10-0231-2x4EES6-K45', 23.1, 'AM', 'N10', 2960),
       ('AM.N10-0280-2x4DES7-K45', 28.0, 'AM', 'N10', 3010),
       ('AM.N10-0339-2x4CES9-K45', 33.9, 'AM', 'N10', 3140),
       ('AM.N10-0349-2x4VES10-K45', 34.9, 'AM', 'N10', 3220),
       ('AM.N10-0425-2x4TES12-K45', 42.5, 'AM', 'N10', 3470),
       ('AM.N10-0524-3x4VES10-K45', 52.4, 'AM', 'N10', 3600),
       ('AM.N10-0637-3x4TES12-K45', 63.7, 'AM', 'N10', 3850),
       ('AK.N10-0161-1x4CES9-K45', 16.1, 'AK', 'N10', 1850),
       ('AK.N10-0276-1x4NES20-K45', 27.6, 'AK', 'N10', 2430),
       ('AKM.N35-0015-1xZF09K-K45', 1.56, 'AKM', 'N35', 1040),
       ('AKM.N35-0027-1xZF15K-K45', 2.73, 'AKM', 'N35', 1110),
       ('AKM.N35-0139-1xZF48K-EVI-K45', 13.9, 'AKM', 'N35', 2800);

INSERT INTO option(name, price)
VALUES ('A1', 120),
       ('C1', 530),
       ('B2', 70);

INSERT INTO unit_option (unit_id, option_id)
VALUES (5,1),
       (5,2),
       (5,3),
       (2,2),
       (2,3),
       (3,1),
       (3,2),
       (4,3);

INSERT INTO registered_user (login, password, registration_date, company, contact_person, telephone, role)
VALUES ('Admin', '$2a$10$sgD/.Rr0k9flPuRme90qwesKqFhH3MdW6KDa7H3YEInVLMy6J1XrC', '01.11.2018', 'RefUnits', 'Admin', '+375555466899', 'ADMIN'),
       ('Manager', '$2a$10$EYDVhahavHzJrCnLhUyW6uBcH26SRexcAeursHIAZWzAy9SaoVsBi', '03.11.2018', 'RefUnits', 'Petr', '+375563286879', 'MANAGER'),
       ('Ivan', '$2a$10$5b/T8naMhVszvomacx5NfeIshF8lQtwtv4/twEZydHVzDUHnrTYDO', '05.11.2018', 'Holodon', 'Ivan', '+375563284412', 'CONSUMER');

INSERT INTO pre_order (registered_user_id, date, status, comment)
VALUES (1, '05.11.2018', 'ACCEPTED', 'test order №1'),
       (2, '06.11.2018', 'ACCEPTED', 'test order №2'),
       (2, '12.11.2018', 'ACCEPTED', 'test order №3'),
       (3, '07.11.2018', 'ACCEPTED', 'test order №4'),
       (3, '14.11.2018', 'ACCEPTED', 'test order №5');

