-- liquibase formatted sql

--changeset DenisTopakov:1
CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(16)  NOT NULL,
    password   VARCHAR(255) NOT NULL,
    first_name VARCHAR(16)  NOT NULL,
    last_name  VARCHAR(16)  NOT NULL,
    phone      VARCHAR(20)  NOT NULL,
    role       VARCHAR(20)  NOT NULL,
    image      VARCHAR(255) NULL
);

--changeset IvanTyapkin:1
CREATE TABLE ads
(
    id             SERIAL PRIMARY KEY,
    ad_title       VARCHAR(23)  NOT NULL,
    ad_description TEXT,
    price          INTEGER,
    author_id      INTEGER      NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    image          VARCHAR(255) NULL
);

-- changeset IrinaSerebryakova:1
CREATE TABLE comments
(
    id         SERIAL PRIMARY KEY,
    text       VARCHAR(500) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ad_id      INTEGER      NOT NULL REFERENCES ads (id) ON DELETE CASCADE,
    author_id  INTEGER      NOT NULL REFERENCES users (id) ON DELETE CASCADE
);







