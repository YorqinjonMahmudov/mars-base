DROP TABLE IF EXISTS work;

DROP TABLE IF EXISTS team_member;

DROP TABLE IF EXISTS team;

DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS block;

CREATE TABLE block
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(20) UNIQUE NOT NULL,
    area     DOUBLE PRECISION,
    location VARCHAR(50)
);


CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    last_name  VARCHAR(20) NOT NULL,
    email      VARCHAR(50) NOT NULL UNIQUE,
    password   VARCHAR(20) NOT NULL,
    birth_date DATE,
    role       VARCHAR,
    block_id   INTEGER     NOT NULL REFERENCES block (id)
);


CREATE TABLE team
(
    id             SERIAL PRIMARY KEY,
    name           VARCHAR(20) NOT NULL UNIQUE,
    team_leader_id INTEGER     NOT NULL REFERENCES users (id)
);

CREATE TABLE team_member
(
    id      SERIAL PRIMARY KEY,
    team_id INTEGER NOT NULL REFERENCES team (id),
    user_id INTEGER NOT NULL REFERENCES users (id)
);


CREATE TABLE work
(
    id             SERIAL PRIMARY KEY,
    title          VARCHAR(20)      NOT NULL,
    description    TEXT             NOT NULL,
    required_money DOUBLE PRECISION NOT NULL UNIQUE,
    status         VARCHAR          NOT NULL,
    start_date     DATE DEFAULT now(),
    finish_date    DATE,
    star           INTEGER,
    team_id        INTEGER          NOT NULL REFERENCES team (id),
    block_id       INTEGER          NOT NULL REFERENCES block (id)
);

