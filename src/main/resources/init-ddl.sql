DROP TABLE IF EXISTS report;
DROP TABLE IF EXISTS work;

DROP TABLE IF EXISTS team_member;

DROP TABLE IF EXISTS team;

DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS block;

CREATE TABLE block
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR UNIQUE NOT NULL,
    area     DOUBLE PRECISION DEFAULT 50,
    location VARCHAR(50)    NOT NULL
);


CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    last_name  VARCHAR(20) NOT NULL,
    email      VARCHAR(50) NOT NULL UNIQUE,
    password   VARCHAR(50) NOT NULL,
    role       VARCHAR     NOT NULL,
    block_id   INTEGER     NOT NULL REFERENCES block (id)
);


CREATE TABLE team
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(20) NOT NULL UNIQUE,
    is_active    BOOLEAN     NOT NULL,
    team_lead_id INTEGER     NOT NULL REFERENCES users (id)
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
    start_date     TIMESTAMP DEFAULT now(),
    finish_date    TIMESTAMP,
    star           INTEGER,
    team_id        INTEGER          NOT NULL REFERENCES team (id),
    block_id       INTEGER          NOT NULL REFERENCES block (id)
);

CREATE TABLE report
(
    id       SERIAL PRIMARY KEY,
    date     DATE NOT NULL DEFAULT now(),
    comments VARCHAR(500),
    work_id   INTEGER   NOT NULL REFERENCES work (id)
);

