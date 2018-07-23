DROP  TABLE  USER;
DROP  TABLE  EDIFICIO;

CREATE TABLE USER (
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL,
  firstname VARCHAR(255) NOT NULL,
  lastname VARCHAR(255) NOT NULL,
  edificio VARCHAR(255) NOT NULL,
  enabled BOOLEAN not null);

CREATE TABLE EDIFICIO (
  edificioid VARCHAR(255) NOT NULL,
  direccion VARCHAR(255) NOT NULL,
  unidades_funcionales INT NOT NULL,
  sum BOOLEAN NOT NULL,
  pileta BOOLEAN NOT NULL,
  enabled BOOLEAN not null);

