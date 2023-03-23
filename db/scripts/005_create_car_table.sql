CREATE TABLE IF NOT EXISTS car
(
    id        SERIAL PRIMARY KEY,
    name      TEXT,
    engine_id INTEGER REFERENCES engine (id) UNIQUE NOT NULL
)