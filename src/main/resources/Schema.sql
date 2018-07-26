DROP  TABLE  USER;
DROP  TABLE  EDIFICIO;
DROP  TABLE  INCIDENCIA;



CREATE TABLE EDIFICIO (
  edificioid int AUTO_INCREMENT PRIMARY KEY NOT NULL,
  direccion VARCHAR(255) NOT NULL,
  unidades_funcionales INT NOT NULL,
  sum BOOLEAN NOT NULL,
  pileta BOOLEAN NOT NULL,
  enabled BOOLEAN not null);

CREATE TABLE USER (
  username  VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL,
  firstname VARCHAR(255) NOT NULL,
  lastname VARCHAR(255) NOT NULL,
  edificio VARCHAR(255) NOT NULL,
  unidad_funcional VARCHAR(255) NOT NULL,
  telefono VARCHAR(255) ,
  celular VARCHAR(255) ,
  enabled BOOLEAN not null);

CREATE TABLE INCIDENCIA (
  incidenciaid int AUTO_INCREMENT PRIMARY KEY NOT NULL,
  tipo VARCHAR(255) NOT NULL,
  titulo VARCHAR(255) NOT NULL,
  descripcion VARCHAR(255) NOT NULL,
  etapa VARCHAR(255) NOT NULL,
  emergencia BOOLEAN not null,
  finalizada BOOLEAN not null,
  edificioid int NOT NULL,
  username  VARCHAR(255) NOT NULL);


