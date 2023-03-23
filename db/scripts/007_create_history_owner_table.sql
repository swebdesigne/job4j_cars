CREATE TABLE IF NOT EXISTS history_owner
(
    id        SERIAL PRIMARY KEY,
    startAt   TIMESTAMP,
    endAt     TIMESTAMP,
    car_id    INTEGER REFERENCES car (id),
    owner_id INTEGER REFERENCES owner (id)
)