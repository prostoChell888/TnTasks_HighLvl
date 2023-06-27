CREATE TABLE person
(
    id        SERIAL PRIMARY KEY,
    full_name VARCHAR
);
CREATE TABLE bank
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR
);
CREATE TABLE person_bank
(
    id        BIGSERIAL PRIMARY KEY,
    person_id INTEGER NOT NULL REFERENCES person,
    bank_id   INTEGER NOT NULL REFERENCES bank,
    UNIQUE (person_id, bank_id)
);