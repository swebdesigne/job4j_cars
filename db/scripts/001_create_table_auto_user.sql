CREATE TABLE auto_user
(
    id       SERIAL PRIMARY KEY,
    login    TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
)
