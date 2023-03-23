CREATE TABLE IF NOT EXISTS owner
(
    id      SERIAL PRIMARY KEY,
    name    TEXT,
    user_id INTEGER REFERENCES auto_user (id) UNIQUE NOT NULL
)