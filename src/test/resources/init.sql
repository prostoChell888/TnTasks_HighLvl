CREATE TABLE IF NOT EXISTS person
(
    id        BIGINT GENERATED ALWAYS AS IDENTITY,
    full_name VARCHAR,

    CONSTRAINT PK_person_id PRIMARY KEY (id)

    );


CREATE TABLE IF NOT EXISTS bank
(
    id   BIGINT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR,

    CONSTRAINT PK_bank_id PRIMARY KEY (id)
    );


CREATE TABLE IF NOT EXISTS person_bank
(
    id        BIGINT GENERATED ALWAYS AS IDENTITY,
    person_id INTEGER NOT NULL REFERENCES person,
    bank_id   INTEGER NOT NULL REFERENCES bank,
    UNIQUE (person_id, bank_id),

    CONSTRAINT PK_person_bank_id PRIMARY KEY (id)
    );

