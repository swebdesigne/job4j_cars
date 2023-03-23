CREATE TABLE auto_user
(
    id       SERIAL PRIMARY KEY,
    login    TEXt NOT NULL UNIQUE,
    password TEXT NOT NULL
)
