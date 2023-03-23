CREATE TABLE IF NOT EXISTS auto_post
(
    id           INTEGER PRIMARY KEY,
    description  VARCHAR,
    created      TIMESTAMP,
    auto_user_id INTEGER REFERENCES auto_post (id) UNIQUE
)