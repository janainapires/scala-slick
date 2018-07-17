# --- !Ups

create table people (
  id bigint NOT NULL,
  name varchar not null,
  age int not null,
  CONSTRAINT people_pkey PRIMARY KEY (id)
);

CREATE TABLE email
(
  id bigint NOT NULL,
  cancelledstatus boolean,
  assunto text,
  conteudo text,
  errormsg text,
  datacadastro timestamp without time zone,
  dataenvio timestamp without time zone,
  situacao bigint,
  usuariointerno bigint,
  tramitavel bigint,
  interessado bigint,
  usuarioenvio bigint,
  remetente bigint,
  email character varying(255),
  evento text,
  codigorastreamento character varying(7),
  dataleiturarastreamento timestamp without time zone,
  dadosrastreamento character varying(500),
  CONSTRAINT email_pkey PRIMARY KEY (id)
)

# --- !Downs

drop table if exists people;
drop table if exists email;
