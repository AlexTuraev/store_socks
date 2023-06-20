-- liquibase formatted sql

-- changeset alex:1
CREATE TABLE "socks" (
    "id" BIGINT primary key,
    "color" VARCHAR,
    "cottonPart" SMALLINT,
    "quantity" INTEGER
);