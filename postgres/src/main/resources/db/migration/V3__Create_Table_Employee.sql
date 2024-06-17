CREATE SEQUENCE employee_seq START 1;
CREATE TABLE employees
(
    id                  BIGINT DEFAULT nextval('employee_seq') PRIMARY KEY,
    first_name          VARCHAR(255),
    last_name           VARCHAR(255),
    age                 INT,
    designation         VARCHAR(255),
    phone_number        VARCHAR(255),
    joined_on           DATE,
    address             VARCHAR(255),
    date_of_birth       DATE,
    created_date        TIMESTAMP,
    last_modified_date  TIMESTAMP,
    created_by_id       BIGINT,
    last_modified_by_id BIGINT,
    FOREIGN KEY (created_by_id) REFERENCES users (id),
    FOREIGN KEY (last_modified_by_id) REFERENCES users (id)
);

