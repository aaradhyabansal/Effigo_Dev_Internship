DROP EXTENSION IF EXISTS citext CASCADE;

DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
CREATE EXTENSION IF NOT EXISTS citext;

CREATE TABLE users (
                       username CITEXT NOT NULL PRIMARY KEY,
                       password VARCHAR(500) NOT NULL,
                       enabled BOOLEAN NOT NULL
);


CREATE TABLE authorities (
                             username CITEXT NOT NULL,
                             authority CITEXT NOT NULL,
                             CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username)
);


CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);
