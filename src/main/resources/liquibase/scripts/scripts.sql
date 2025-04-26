-- liquibase formatted sql

-- changeset IrinaSerebryakova:1
CREATE TABLE comments
(
    id BIGSERIAL PRIMARY KEY,
    text TEXT,
    ad VARCHAR(255),
    author VARCHAR(255),
    createdAt TIMESTAMP(6)
);