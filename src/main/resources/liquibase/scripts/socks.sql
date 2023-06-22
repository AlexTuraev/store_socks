-- liquibase formatted sql

-- changeset alex:1
CREATE TABLE "socks_model" (
    --"id" BIGINT auto_increment primary key,
    "id" SERIAL,
    "color" VARCHAR,
    "cotton_part" SMALLINT,
    "quantity" INTEGER
);

-- changeset alex:2
ALTER TABLE "socks_model"
    ADD CONSTRAINT color_cottonpart UNIQUE (color, cotton_part);

-- changeset alex:3
ALTER TABLE "socks_model"
    ADD CONSTRAINT cotton_part_constraint CHECK ( cotton_part >= 0 AND cotton_part <= 100);

ALTER TABLE "socks_model"
    ADD CONSTRAINT quantity_constraint CHECK ( quantity > 0);