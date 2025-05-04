-- liquibase formatted sql



--changeset DenisTopakov:1
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(16) NOT NULL,
    password VARCHAR(16) NOT NULL,
    first_name VARCHAR(16) NOT NULL,
    last_name VARCHAR(16) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    role VARCHAR(20) NOT NULL,
    image VARCHAR(255) NOT NULL
);

--changeset IvanTyapkin:1
ALTER TABLE users
    ALTER COLUMN username TYPE VARCHAR(27);

--changeset IvanTyapkin:2
ALTER TABLE users
    ALTER COLUMN password TYPE TEXT;

--changeset IvanTyapkin:3
ALTER TABLE users
    ALTER COLUMN image DROP NOT NULL;