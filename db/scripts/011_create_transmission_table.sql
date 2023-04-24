CREATE TABLE IF NOT EXISTS transmission
(
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);
INSERT INTO transmission (name) VALUES ('Ручная');
INSERT INTO transmission (name) VALUES ('Автомат');
INSERT INTO transmission (name) VALUES ('Робот');
