CREATE SEQUENCE department_seq START 1;
CREATE TABLE departments
(
    id   BIGINT DEFAULT nextval('department_seq') PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);