CREATE SEQUENCE role_seq START 1;
CREATE TABLE roles
(
    id   BIGINT DEFAULT nextval('role_seq') PRIMARY KEY,
    name VARCHAR(255)
);