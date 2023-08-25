CREATE DOMAIN status AS varchar(30)
NOT NULL DEFAULT VALUE 'process' 
CHECK( value in ('process', 'terminated','error') );
CREATE TABLE "user" (
  "id" serial,
  "alias" varchar(200),
  "correo" varchar(255),
  "full_name" varchar(255),
  "phone" varchar(12),
  PRIMARY KEY ("id")
);

CREATE TABLE "proyect" (
  "id" serial,
  "name" varchar(200),
  "img" text,
  "user" integer,
  PRIMARY KEY ("id"),
  CONSTRAINT "fk_user_proyect"
  FOREIGN KEY("user
CREATE DOMAIN status AS varchar(30)
NOT NULL 
CHECK( VALUE in ('process', 'terminated','error') );

CREATE TABLE "user" (
  "id" serial,
  "alias" varchar(200),
  "correo" varchar(255),
  "full_name" varchar(255),
  "phone" varchar(12),
  PRIMARY KEY ("id")
);

CREATE TABLE "proyect" (
  "id" serial,
  "name" varchar(200),
  "img" text,
  "user" integer,
  PRIMARY KEY ("id"),
  CONSTRAINT "fk_user_proyect"
  FOREIGN KEY("user") REFERENCES public."user"("id")
);

CREATE TABLE "check_list" (
  "id" serial,
  "name" varchar(200),
  "created" date,
  "edited" date,
  "status" status,
  "proyect" integer,
  PRIMARY KEY ("id"),
  CONSTRAINT "fk_proyect_check_list"
  FOREIGN KEY("proyect") REFERENCES public."proyect"("id")
);

CREATE TABLE "item_check_list" (
  "id" serial,
  "question" varchar(255),
  "answer" boolean,
  "comment" text,
  "check_list" integer,
  PRIMARY KEY ("id"),
  CONSTRAINT "fk_check_list_item_check_list"
  FOREIGN KEY("check_list") REFERENCES public."check_list"("id")
);
