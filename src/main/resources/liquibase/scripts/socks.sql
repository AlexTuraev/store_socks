-- liquibase formatted sql

-- changeset alex:1
CREATE TABLE "socks_model" (
    --"id" BIGINT auto_increment primary key,
    "id" SERIAL,
    "color" VARCHAR,
    "cotton_part" SMALLINT,
    "quantity" INTEGER
);