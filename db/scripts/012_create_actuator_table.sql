CREATE TABLE IF NOT EXISTS actuator
(
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);
INSERT INTO actuator (name) VALUES ('Передний');
INSERT INTO actuator (name) VALUES ('Задний');
INSERT INTO actuator (name) VALUES ('Полный');
