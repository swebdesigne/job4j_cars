CREATE TABLE IF NOT EXISTS car
(
    id              SERIAL PRIMARY KEY,
    name            TEXT,
    model_id        INTEGER REFERENCES model (id)        NOT NULL,
    engine_id       INTEGER REFERENCES engine (id)       NOT NULL,
    year_id         INTEGER REFERENCES year (id)         NOT NULL,
    transmission_id INTEGER REFERENCES transmission (id) NOT NULL,
    actuator_id     INTEGER REFERENCES actuator (id)     NOT NULL
);
INSERT INTO car (name, model_id, engine_id, year_id, transmission_id, actuator_id)
VALUES ('LADA', 1, 1, 1, 1, 1);
INSERT INTO car (name, model_id, engine_id, year_id, transmission_id, actuator_id)
VALUES ('LADA', 1, 2, 2, 2, 2);
INSERT INTO car (name, model_id, engine_id, year_id, transmission_id, actuator_id)
VALUES ('LADA', 1, 3, 3, 3, 3);
INSERT INTO car (name, model_id, engine_id, year_id, transmission_id, actuator_id)
VALUES ('LADA', 2, 3, 3, 1, 1);
INSERT INTO car (name, model_id, engine_id, year_id, transmission_id, actuator_id)
VALUES ('LADA', 2, 4, 4, 2, 2);
INSERT INTO car (name, model_id, engine_id, year_id, transmission_id, actuator_id)
VALUES ('Audi', 4, 5, 5, 3, 1);
INSERT INTO car (name, model_id, engine_id, year_id, transmission_id, actuator_id)
VALUES ('Audi', 4, 6, 6, 1, 2);
INSERT INTO car (name, model_id, engine_id, year_id, transmission_id, actuator_id)
VALUES ('Audi', 4, 7, 7, 2, 3);
INSERT INTO car (name, model_id, engine_id, year_id, transmission_id, actuator_id)
VALUES ('Audi', 3, 7, 8, 3, 1);
INSERT INTO car (name, model_id, engine_id, year_id, transmission_id, actuator_id)
VALUES ('Audi', 3, 8, 8, 1, 2);
INSERT INTO car (name, model_id, engine_id, year_id, transmission_id, actuator_id)
VALUES ('Audi', 3, 9, 9, 2, 3);
INSERT INTO car (name, model_id, engine_id, year_id, transmission_id, actuator_id)
VALUES ('Audi', 5, 8, 8, 3, 1);
INSERT INTO car (name, model_id, engine_id, year_id, transmission_id, actuator_id)
VALUES ('Audi', 5, 9, 10, 2, 2);
INSERT INTO car (name, model_id, engine_id, year_id, transmission_id, actuator_id)
VALUES ('Audi', 5, 10, 11, 3, 3);