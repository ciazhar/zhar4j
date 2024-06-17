CREATE SEQUENCE user_seq START 1;
CREATE TABLE users
(
    id                  BIGINT DEFAULT nextval('user_seq') PRIMARY KEY,
    username            VARCHAR(255),
    password            VARCHAR(255),
    role_id             BIGINT,
    created_date        TIMESTAMP,
    last_modified_date  TIMESTAMP,
    created_by_id       BIGINT,
    last_modified_by_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES roles (id),
    FOREIGN KEY (created_by_id) REFERENCES users (id),
    FOREIGN KEY (last_modified_by_id) REFERENCES users (id)
);