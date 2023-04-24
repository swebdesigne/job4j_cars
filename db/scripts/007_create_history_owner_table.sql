CREATE TABLE IF NOT EXISTS history_owner
(
    id        SERIAL PRIMARY KEY,
    startAt   TIMESTAMP,
    endAt     TIMESTAMP,
    car_id    INTEGER REFERENCES car (id),
    owner_id INTEGER REFERENCES owner (id)
);
INSERT INTO history_owner (startAt, endAt, car_id, owner_id) VALUES (now(), now(), 1, 1);
INSERT INTO history_owner (startAt, endAt, car_id, owner_id) VALUES (now(), now(), 2, 2);
INSERT INTO history_owner (startAt, endAt, car_id, owner_id) VALUES (now(), now(), 3, 3);
INSERT INTO history_owner (startAt, endAt, car_id, owner_id) VALUES (now(), now(), 4, 4);
INSERT INTO history_owner (startAt, endAt, car_id, owner_id) VALUES (now(), now(), 5, 1);
INSERT INTO history_owner (startAt, endAt, car_id, owner_id) VALUES (now(), now(), 6, 2);
INSERT INTO history_owner (startAt, endAt, car_id, owner_id) VALUES (now(), now(), 7, 3);
INSERT INTO history_owner (startAt, endAt, car_id, owner_id) VALUES (now(), now(), 8, 4);
INSERT INTO history_owner (startAt, endAt, car_id, owner_id) VALUES (now(), now(), 9, 1);
INSERT INTO history_owner (startAt, endAt, car_id, owner_id) VALUES (now(), now(), 10, 1);
INSERT INTO history_owner (startAt, endAt, car_id, owner_id) VALUES (now(), now(), 11, 2);
INSERT INTO history_owner (startAt, endAt, car_id, owner_id) VALUES (now(), now(), 12, 2);
INSERT INTO history_owner (startAt, endAt, car_id, owner_id) VALUES (now(), now(), 13, 3);
INSERT INTO history_owner (startAt, endAt, car_id, owner_id) VALUES (now(), now(), 14, 4);

