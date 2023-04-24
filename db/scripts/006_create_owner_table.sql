CREATE TABLE IF NOT EXISTS owner
(
    id      SERIAL PRIMARY KEY,
    name    TEXT,
    user_id INTEGER REFERENCES auto_user (id) NOT NULL
);
INSERT INTO owner (name, user_id) VALUES ('Alina', 1);
INSERT INTO owner (name, user_id) VALUES ('Igor', 2);
INSERT INTO owner (name, user_id) VALUES ('Boris', 3);
INSERT INTO owner (name, user_id) VALUES ('Petr', 4);